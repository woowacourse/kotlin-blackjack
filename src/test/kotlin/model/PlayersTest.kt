package model

import model.CardsTest.Companion.cardOf
import model.card.Card
import model.card.CardName
import model.card.CardRank
import model.card.Shape
import model.participant.Player
import model.participant.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class PlayersTest {
    @Test
    fun `플레이어의 수는 1명 이상이어야 한다`() {
        val cards1 =
            cardOf(
                Card(CardRank.SIX, Shape.CLUB),
                Card(CardRank.NINE, Shape.SPADE),
            )

        val cards2 =
            cardOf(
                Card(CardRank.TEN, Shape.CLUB),
                Card(CardRank.EIGHT, Shape.SPADE),
            )

        val player1 = Player("joy", cards1, 10000f)
        val player2 = Player("jay", cards2, 10000f)

        val players = listOf(player1, player2)
        assertDoesNotThrow { Players(players) }
    }

    @Test
    fun `플레이어들의 카드네임을 모두 알 수 있다`() {
        val cards1 =
            cardOf(
                Card(CardRank.SIX, Shape.CLUB),
                Card(CardRank.NINE, Shape.SPADE),
            )
        val cards2 =
            cardOf(
                Card(CardRank.TEN, Shape.CLUB),
                Card(CardRank.EIGHT, Shape.SPADE),
            )

        val player1 = Player("joy", cards1, 10000f)
        val player2 = Player("jay", cards2, 10000f)

        val players = Players(listOf(player1, player2))

        val expected =
            listOf(
                listOf(CardName(CardRank.SIX.name, Shape.CLUB.name), CardName(CardRank.NINE.name, Shape.SPADE.name)),
                listOf(CardName(CardRank.TEN.name, Shape.CLUB.name), CardName(CardRank.EIGHT.name, Shape.SPADE.name)),
            )

        assertThat(players.cardNames).isEqualTo(expected)
    }

    @Test
    fun `플레이어들의 점수를 모두 알 수 있다`() {
        val cards1 =
            cardOf(
                Card(CardRank.SIX, Shape.CLUB),
                Card(CardRank.NINE, Shape.SPADE),
            )
        val cards2 =
            cardOf(
                Card(CardRank.TEN, Shape.CLUB),
                Card(CardRank.EIGHT, Shape.SPADE),
            )

        val player1 = Player("joy", cards1, 10000f)
        val player2 = Player("jay", cards2, 10000f)

        val players = Players(listOf(player1, player2))
        assertThat(players.scores)
            .containsExactly(15, 18)
    }
}
