package blackjack.model

import blackjack.model.card.Card
import blackjack.model.participant.Dealer
import blackjack.model.participant.PlayerName
import blackjack.model.participant.Players
import blackjack.model.result.GameResultType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackGameTest {
    @Test
    fun `게임의 최종 승패 결과를 계산한다`() {
        // given
        val dealer = Dealer()
        val players = Players.from(listOf("olive", "seogi", "chae"))

        dealer.receiveCard(listOf(Card.of("8", "하트")))
        players.playerGroup.forEachIndexed { idx, player ->
            player.receiveCard(listOf(Card.of(denominationValues[idx], "하트")))
        }

        // when
        val gameResultStorage = BlackjackGame.calculateGameResult(dealer, players)

        // then
        assertThat(gameResultStorage.dealerResult.results)
            .containsEntry(GameResultType.LOSE, 1)
            .containsEntry(GameResultType.WIN, 1)
            .containsEntry(GameResultType.DRAW, 1)
        assertThat(gameResultStorage.playersResult.results)
            .containsEntry(PlayerName("olive"), GameResultType.LOSE)
            .containsEntry(PlayerName("seogi"), GameResultType.WIN)
            .containsEntry(PlayerName("chae"), GameResultType.DRAW)
    }

    companion object {
        private val denominationValues = listOf("2", "K", "8")
    }
}
