package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackGameTest {
    @Test
    fun `게임 시작 시 딜러와 플레이어가 2장의 카드를 받는다`() {
        // given
        val dealer = Dealer()
        val players = Players.from(listOf("olive", "seogi"))

        // when
        BlackjackGame.initCard(dealer, players, TestCardProvider)

        // then
        val expected = listOf(Card.of("K", "하트"), Card.of("K", "하트"))
        assertThat(dealer.getCards()).isEqualTo(expected)
        players.playerGroup.forEach {
            assertThat(it.getCards()).isEqualTo(expected)
        }
    }

    @Test
    fun `게임의 최종 승패 결과를 계산한다`() {
        // given
        val dealer = Dealer()
        val players = Players.from(listOf("olive", "seogi", "chae"))

        dealer.receiveCard(Card.of("8", "하트"))
        players.playerGroup.forEachIndexed { idx, player ->
            player.receiveCard(Card.of(denominationValues[idx], "하트"))
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
