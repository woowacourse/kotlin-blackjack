package domain.judge

import domain.card.Card
import domain.card.CardValue
import domain.card.Shape
import domain.gamer.Dealer
import domain.gamer.Player
import domain.gamer.cards.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RefereeTest() {

    @Test
    fun `참가자가 한명일 때 딜러가 버스트이고, 플레이어가 21 이하 일 때 플레이어가 이긴다`() {
        val dealer = Dealer(
            Cards(
                listOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.QUEEN),
                    Card(Shape.SPADE, CardValue.THREE)
                )
            )

        )
        val player = Player("jack", Cards(listOf(Card(Shape.HEART, CardValue.JACK))))
        val referee = Referee(dealer, listOf(player)).judgePlayersResult()
        assertThat(referee).isEqualTo(mapOf("jack" to Result.WIN))
    }

    @Test
    fun `참가자가 한명일 때 딜러가 버스트이고, 플레이어가 버스트 일 때 플레이어가 진다`() {
        val dealer = Dealer(
            Cards(
                listOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.THREE)
                )
            )

        )
        val player = Player(
            "jack",
            Cards(
                listOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.THREE)
                )
            )
        )
        val referee = Referee(dealer, listOf(player)).judgePlayersResult()
        assertThat(referee).isEqualTo(mapOf("jack" to Result.LOSS))
    }

    @Test
    fun `참가자가 한명일 때 딜러가 21이하이고, 플레이어가 버스트일 때 플레이어가 진다`() {
        val dealer = Dealer(Cards(listOf(Card(Shape.HEART, CardValue.JACK))))
        val player = Player(
            "jack",
            Cards(
                listOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.THREE)
                )
            )

        )
        val referee = Referee(dealer, listOf(player)).judgePlayersResult()
        assertThat(referee).isEqualTo(mapOf("jack" to Result.LOSS))
    }

    @Test
    fun `플레이어 두명이 각 15,21의 결과값이고 딜러가 20일 때, 패,승을 반환한다`() {
        val dealer =
            Dealer(Cards(listOf(Card(Shape.HEART, CardValue.JACK), Card(Shape.HEART, CardValue.QUEEN))))
        val player1 = Player(
            "jack",
            Cards(
                listOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.FIVE)
                )
            )

        )
        val player2 = Player(
            "king",
            Cards(
                listOf(
                    Card(Shape.SPADE, CardValue.QUEEN),
                    Card(Shape.SPADE, CardValue.KING),
                    Card(Shape.SPADE, CardValue.ACE)
                )
            )
        )
        val referee = Referee(dealer, listOf(player1, player2)).judgePlayersResult()
        assertThat(referee).isEqualTo(mapOf("jack" to Result.LOSS, "king" to Result.WIN))
    }

    @Test
    fun `플레이어 세명이 각 10,23,17 결과값이고 딜러가 17 일 때 패,패,무 를 반환한다`() {
        val dealer =
            Dealer(Cards(listOf(Card(Shape.HEART, CardValue.SEVEN), Card(Shape.HEART, CardValue.QUEEN))))
        val player1 = Player(
            "jack",
            Cards(
                listOf(
                    Card(Shape.DIAMOND, CardValue.FIVE),
                    Card(Shape.SPADE, CardValue.FIVE)
                )
            )

        )
        val player2 = Player(
            "king",
            Cards(
                listOf(
                    Card(Shape.CLOVER, CardValue.QUEEN),
                    Card(Shape.DIAMOND, CardValue.KING),
                    Card(Shape.DIAMOND, CardValue.THREE)
                )
            )

        )
        val player3 = Player(
            "queen",
            Cards(
                listOf(
                    Card(Shape.HEART, CardValue.QUEEN),
                    Card(Shape.CLOVER, CardValue.SEVEN)
                )
            )
        )
        val referee = Referee(
            dealer,
            listOf(player1, player2, player3)
        ).judgePlayersResult()
        assertThat(referee).isEqualTo(mapOf("jack" to Result.LOSS, "king" to Result.LOSS, "queen" to Result.DRAW))
    }

    @Test
    fun `참가자가 한명일 때 딜러가 블랙잭이고, 플레이어가 3장 합이 21일 때 플레이어가 진다`() {
        val dealer = Dealer(Cards(listOf(Card(Shape.HEART, CardValue.JACK), Card(Shape.HEART, CardValue.ACE))))
        val player = Player(
            "jack",
            Cards(
                listOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.ACE)
                )
            )

        )
        val referee = Referee(dealer, listOf(player)).judgePlayersResult()
        assertThat(referee).isEqualTo(mapOf("jack" to Result.LOSS))
    }

    @Test
    fun `참가자가 한명일 때 딜러가 블랙잭이고, 플레이어가 블랙잭일 때 무승부이다`() {
        val dealer = Dealer(Cards(listOf(Card(Shape.HEART, CardValue.JACK), Card(Shape.HEART, CardValue.ACE))))
        val player = Player(
            "jack",
            Cards(
                listOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.ACE)
                )
            )

        )
        val referee = Referee(dealer, listOf(player)).judgePlayersResult()
        assertThat(referee).isEqualTo(mapOf("jack" to Result.DRAW))
    }

    @Test
    fun `참가자가 한명일 때 딜러가 3장 합이 21이고, 플레이어가 블랙잭일 때 플레이어가 이긴다`() {
        val dealer = Dealer(
            Cards(
                listOf(
                    Card(Shape.HEART, CardValue.JACK),
                    Card(Shape.HEART, CardValue.FIVE),
                    Card(Shape.HEART, CardValue.SIX)
                )
            )
        )
        val player = Player(
            "jack",
            Cards(
                listOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.ACE)
                )
            )

        )
        val referee = Referee(dealer, listOf(player)).judgePlayersResult()
        assertThat(referee).isEqualTo(mapOf("jack" to Result.WIN))
    }
}
