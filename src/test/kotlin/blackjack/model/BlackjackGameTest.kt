package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackGameTest {
    @Test
    fun `게임 승패 결과 계산 테스트`() {
        // given
        val players = Players.from(listOf("olive", "seogi", "chae"))
        val dealer = Dealer()

        players.playerGroup.forEachIndexed { idx, player ->
            player.receiveCard(Card.of(denominationValues[idx], "하트"))
        }
        dealer.receiveCard(Card.of("8", "하트"))

        // when
        BlackjackGame.updateGameResult(dealer, players)

        // then
        assertThat(players.playersResult.results)
            .containsEntry(PlayerName("olive"), GameResultType.LOSE)
            .containsEntry(PlayerName("seogi"), GameResultType.WIN)
            .containsEntry(PlayerName("chae"), GameResultType.DRAW)

        assertThat(dealer.result.results)
            .containsEntry(GameResultType.LOSE, 1)
            .containsEntry(GameResultType.WIN, 1)
            .containsEntry(GameResultType.DRAW, 1)
    }

    companion object {
        private val denominationValues = listOf("2", "K", "8")
    }
}
