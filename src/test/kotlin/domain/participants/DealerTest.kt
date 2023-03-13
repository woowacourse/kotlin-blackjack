package domain.participants

import domain.card.Card
import domain.card.CardValue
import domain.card.Cards
import domain.card.Shape
import domain.result.Result
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러 카드의 합이 16을 넘었을 경우 true를 반환한다`() {
        val dealer =
            Dealer(Cards(mutableListOf(Card(Shape.SPADE, CardValue.JACK), Card(Shape.SPADE, CardValue.JACK))))
        assertThat(dealer.checkOverCondition()).isTrue
    }

    @Test
    fun `딜러 카드의 합이 16을 넘지 않았을 경우 false를 반환한다`() {
        val dealer = Dealer(Cards(mutableListOf(Card(Shape.SPADE, CardValue.JACK))))
        assertThat(dealer.checkOverCondition()).isFalse
    }

    @Test
    fun `참가자가 블랙잭이고 딜러가 합이 21일 경우, 플레이어가 이긴다`() {
        val dealerCards = Cards(
            mutableListOf(
                Card(Shape.SPADE, CardValue.JACK),
                Card(Shape.SPADE, CardValue.QUEEN),
                Card(Shape.SPADE, CardValue.ACE)
            )
        )
        val dealer = Dealer(dealerCards)
        val playerCards = Cards(mutableListOf(Card(Shape.HEART, CardValue.JACK), Card(Shape.HEART, CardValue.ACE)))
        val player = Player("jack", playerCards, Money(100))

        assertThat(dealer.judgePlayerResult(player.ownCards)).isEqualTo(Result.BLACKJACK_WIN)
    }

    @Test
    fun `참가자와 딜러 모두 블랙잭인 경우, 비긴다`() {
        val dealer = Dealer(
            Cards(
                mutableListOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.ACE)
                )
            )
        )
        val player =
            Player("jack", Cards(mutableListOf(Card(Shape.HEART, CardValue.JACK), Card(Shape.HEART, CardValue.ACE))), Money(100))

        val result = dealer.judgePlayerResult(player.ownCards)

        assertThat(result).isEqualTo(Result.DRAW)
    }

    @Test
    fun `참가자가 합이 21이고 딜러가 블랙잭인 경우, 플레이어가 진다`() {
        val dealer = Dealer(
            Cards(
                mutableListOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.ACE)
                )
            )
        )
        val player =
            Player(
                "jack",
                Cards(
                    mutableListOf(
                        Card(Shape.HEART, CardValue.JACK),
                        Card(Shape.HEART, CardValue.QUEEN),
                        Card(Shape.HEART, CardValue.ACE)
                    )
                ),
                Money(100)
            )

        val result = dealer.judgePlayerResult(player.ownCards)

        assertThat(result).isEqualTo(Result.LOSS)
    }

    @Test
    fun `딜러가 버스트이고, 플레이어가 21 이하 일 때 플레이어가 이긴다`() {
        val dealer = Dealer(
            Cards(
                mutableListOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.QUEEN),
                    Card(Shape.SPADE, CardValue.THREE)
                )
            )
        )
        val player = Player("jack", Cards(mutableListOf(Card(Shape.HEART, CardValue.JACK))), Money(100))

        val result = dealer.judgePlayerResult(player.ownCards)

        assertThat(result).isEqualTo(Result.WIN)
    }

    @Test
    fun `딜러가 버스트이고, 플레이어가 버스트 일 때 플레이어가 진다`() {
        val dealer = Dealer(
            Cards(
                mutableListOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.THREE)
                )
            )
        )
        val player = Player(
            "jack",
            Cards(
                mutableListOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.THREE)
                )
            ),
            Money(100)
        )

        val result = dealer.judgePlayerResult(player.ownCards)

        assertThat(result).isEqualTo(Result.LOSS)
    }

    @Test
    fun `딜러가 21이하이고, 플레이어가 버스트일 때 플레이어가 진다`() {
        val dealer = Dealer(Cards(mutableListOf(Card(Shape.HEART, CardValue.JACK))))
        val player = Player(
            "jack",
            Cards(
                mutableListOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.THREE)
                )
            ),
            Money(100)
        )

        val result = dealer.judgePlayerResult(player.ownCards)

        assertThat(result).isEqualTo(Result.LOSS)
    }

    @Test
    fun `딜러가 21이고, 플레이어도 21일 때 비긴다`() {
        val dealer = Dealer(
            Cards(
                mutableListOf(
                    Card(Shape.HEART, CardValue.JACK),
                    Card(Shape.HEART, CardValue.FIVE),
                    Card(Shape.HEART, CardValue.SIX)
                )
            )
        )
        val player = Player(
            "jack",
            Cards(
                mutableListOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.SIX),
                    Card(Shape.SPADE, CardValue.FIVE)
                )
            ),
            Money(100)
        )

        val result = dealer.judgePlayerResult(player.ownCards)

        assertThat(result).isEqualTo(Result.DRAW)
    }
}
