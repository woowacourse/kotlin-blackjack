package entity

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어가 가진 카드의 숫자 합이 21 미만이면 한장의 카드를 더 받을 수 있다`() {
        val dealer = Dealer(Cards(listOf(Card(CardType.SPADE, CardNumber.TEN))))
        val isDistributable = dealer.isDistributable()

        Assertions.assertThat(isDistributable).isTrue
    }

    @Test
    fun `플레이어가 가진 카드의 숫자 합이 21 이상이면 한장의 카드를 더 받을 수 없다`() {
        val dealer = Dealer(Cards(listOf(Card(CardType.SPADE, CardNumber.TEN), Card(CardType.SPADE, CardNumber.TEN), Card(CardType.SPADE, CardNumber.TWO))))
        val isDistributable = dealer.isDistributable()

        Assertions.assertThat(isDistributable).isFalse
    }

    @Test
    fun `플레이어 카드 숫자의 합이 21이고 딜러 카드 숫자의 합이 21이면 무승부이다`() {
        // given
        val player1 = Player(
            UserInformation(Name("test"), BettingMoney(0)),
            Cards(
                listOf(
                    Card(CardType.HEART, CardNumber.TEN),
                    Card(CardType.SPADE, CardNumber.TEN),
                    Card(CardType.CLUB, CardNumber.ACE)
                )
            )
        )
        val dealer = Dealer(
            Cards(
                listOf(
                    Card(CardType.HEART, CardNumber.TEN),
                    Card(CardType.SPADE, CardNumber.TEN),
                    Card(CardType.CLUB, CardNumber.ACE)
                )
            )
        )
        val results = player1.determineGameResult(dealer.cardsNumberSum())

        // when
        val except = player1 to GameResultType.DRAW

        // then
        Assertions.assertThat(results).isEqualTo(except)
    }

    @Test
    fun `플레이어 카드 숫자의 합이 22이고 딜러 카드 숫자의 합이 22이면 무승부이다`() {
        // given
        val player1 = Player(
            UserInformation(Name("test"), BettingMoney(0)),
            Cards(
                listOf(
                    Card(CardType.HEART, CardNumber.TEN),
                    Card(CardType.SPADE, CardNumber.TEN),
                    Card(CardType.CLUB, CardNumber.TWO)
                )
            )
        )
        val dealer = Dealer(
            Cards(
                listOf(
                    Card(CardType.HEART, CardNumber.TEN),
                    Card(CardType.SPADE, CardNumber.TEN),
                    Card(CardType.CLUB, CardNumber.TWO)
                )
            )
        )
        val results = player1.determineGameResult(dealer.cardsNumberSum())

        // when
        val except = player1 to GameResultType.DRAW

        // then
        Assertions.assertThat(results).isEqualTo(except)
    }

    @Test
    fun `플레이어 카드 숫자의 합이 19이고 딜러 카드 숫자의 합이 22이면 플레이어의 승리이다`() {
        // given
        val player1 = Player(
            UserInformation(Name("test"), BettingMoney(0)),
            Cards(
                listOf(
                    Card(CardType.HEART, CardNumber.TEN), Card(CardType.SPADE, CardNumber.NINE)
                )
            )
        )
        val dealer = Dealer(
            Cards(
                listOf(
                    Card(CardType.HEART, CardNumber.TEN),
                    Card(CardType.SPADE, CardNumber.TEN),
                    Card(CardType.CLUB, CardNumber.TWO)
                )
            )
        )
        val results = player1.determineGameResult(dealer.cardsNumberSum())

        // when
        val except = player1 to GameResultType.WIN

        // then
        Assertions.assertThat(results).isEqualTo(except)
    }

    @Test
    fun `플레이어 카드 숫자의 합이 21이고 딜러 카드 숫자의 합이 20이면 플레이어의 승리이다`() {
        // given
        val player1 = Player(
            UserInformation(Name("test"), BettingMoney(0)),
            Cards(
                listOf(
                    Card(CardType.HEART, CardNumber.SEVEN),
                    Card(CardType.HEART, CardNumber.SEVEN),
                    Card(CardType.HEART, CardNumber.SEVEN),
                )
            )
        )
        val dealer = Dealer(
            Cards(
                listOf(
                    Card(CardType.HEART, CardNumber.TEN), Card(CardType.SPADE, CardNumber.TEN)
                )
            )
        )
        val results = player1.determineGameResult(dealer.cardsNumberSum())

        // when
        val except = player1 to GameResultType.WIN

        // then
        Assertions.assertThat(results).isEqualTo(except)
    }

    @Test
    fun `플레이어 카드 숫자의 합이 20이고 딜러 카드 숫자의 합이 21이면 플레이어의 패배이다`() {
        // given
        val player1 = Player(
            UserInformation(Name("test"), BettingMoney(0)),
            Cards(
                listOf(
                    Card(CardType.HEART, CardNumber.TEN), Card(CardType.SPADE, CardNumber.TEN)
                )
            )
        )
        val dealer = Dealer(
            Cards(
                listOf(
                    Card(CardType.HEART, CardNumber.TEN),
                    Card(CardType.SPADE, CardNumber.TEN),
                    Card(CardType.CLUB, CardNumber.ACE)
                )
            )
        )
        val results = player1.determineGameResult(dealer.cardsNumberSum())

        // when
        val except = player1 to GameResultType.LOSE

        // then
        Assertions.assertThat(results).isEqualTo(except)
    }

    @Test
    fun `플레이어 카드 숫자의 합이 22이고 딜러 카드 숫자의 합이 21이면 플레이어의 패배이다`() {
        // given
        val player1 = Player(
            UserInformation(Name("test"), BettingMoney(0)),
            Cards(
                listOf(
                    Card(CardType.HEART, CardNumber.TEN),
                    Card(CardType.SPADE, CardNumber.TEN),
                    Card(CardType.CLUB, CardNumber.TWO)
                )
            )
        )
        val dealer = Dealer(
            Cards(
                listOf(
                    Card(CardType.HEART, CardNumber.TEN),
                    Card(CardType.SPADE, CardNumber.TEN),
                    Card(CardType.CLUB, CardNumber.ACE)
                )
            )
        )
        val results = player1.determineGameResult(dealer.cardsNumberSum())

        // when
        val except = player1 to GameResultType.LOSE

        // then
        Assertions.assertThat(results).isEqualTo(except)
    }
}
