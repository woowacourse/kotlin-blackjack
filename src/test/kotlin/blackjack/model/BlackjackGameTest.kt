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
            .containsEntry(PlayerName("olive"), GameResult.LOSE)
            .containsEntry(PlayerName("seogi"), GameResult.WIN)
            .containsEntry(PlayerName("chae"), GameResult.DRAW)

        assertThat(dealer.result.results)
            .containsEntry(GameResult.LOSE, 1)
            .containsEntry(GameResult.WIN, 1)
            .containsEntry(GameResult.DRAW, 1)
    }

    companion object {
        private val denominationValues = listOf("2", "K", "8")
    }
}
