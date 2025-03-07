package model

import model.CardsTest.Companion.cardOf
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

        val player1 = Player("joy", cards1)
        val player2 = Player("jay", cards2)

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

        val player1 = Player("joy", cards1)
        val player2 = Player("jay", cards2)

        val players = Players(listOf(player1, player2))

        // 예상 결과: 각 플레이어의 카드 네임 리스트
        val expected =
            listOf(
                listOf("6클로버", "9스페이드"),
                listOf("10클로버", "8스페이드"),
            )

        assertThat(players.getPlayerCardNames()).isEqualTo(expected)
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

        val player1 = Player("joy", cards1)
        val player2 = Player("jay", cards2)

        val players = Players(listOf(player1, player2))
        assertThat(players.getPlayersScores())
            .containsExactly(15, 18)
    }
}
