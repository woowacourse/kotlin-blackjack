package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import util.TestCards
import view.displayNames

class PlayersTest {
    @Test
    fun `플레이어의 수는 1명 이상이어야 한다`() {
        val cards1 =
            listOf(
                Card.of(CardRank.SIX, Shape.CLUB),
                Card.of(CardRank.NINE, Shape.SPADE),
            )

        val cards2 =
            listOf(
                Card.of(CardRank.TEN, Shape.CLUB),
                Card.of(CardRank.EIGHT, Shape.SPADE),
            )

        val player1 = Player("joy", Hand(cards1))
        val player2 = Player("jay", Hand(cards2))

        val players = listOf(player1, player2)
        assertDoesNotThrow { Players(players) }
    }

    @Test
    fun `플레이어들의 카드네임을 모두 알 수 있다`() {
        val cards1 =
            listOf(
                TestCards.CLUB_SIX,
                TestCards.SPADE_NINE,
            )
        val cards2 =
            listOf(
                TestCards.CLUB_TEN,
                TestCards.SPADE_EIGHT,
            )

        val player1 = Player("joy", Hand(cards1))
        val player2 = Player("jay", Hand(cards2))

        val players = Players(listOf(player1, player2))

        val expected =
            listOf(
                TestCards.CLUB_SIX,
                TestCards.SPADE_NINE,
            )

        val playersCardNames =
            players.map { player ->
                player.getHand().handCards.displayNames()
            }

        assertThat(playersCardNames).isEqualTo(expected)
    }

    @Test
    fun `플레이어들의 점수를 모두 알 수 있다`() {
        val cards1 =
            listOf(
                TestCards.CLUB_SIX,
                TestCards.SPADE_NINE,
            )
        val cards2 =
            listOf(
                TestCards.CLUB_TEN,
                TestCards.SPADE_EIGHT,
            )

        val player1 = Player("joy", Hand(cards1))
        val player2 = Player("jay", Hand(cards2))

        val players = Players(listOf(player1, player2))
        assertThat(players.getPlayersScores())
            .containsExactly(15, 18)
    }
}
