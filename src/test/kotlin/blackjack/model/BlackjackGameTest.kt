package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackGameTest {
    @Test
    fun `게임 승패 결과 계산 테스트`() {
        // given
        val player1 = Player(PlayerName("olive"))
        val player2 = Player(PlayerName("seogi"))
        val player3 = Player(PlayerName("chae"))
        val players = Players(listOf(player1, player2, player3))
        val dealer = Dealer()

        player1.receiveCard(Card("2", "하트"))
        player2.receiveCard(Card("K", "하트"))
        player3.receiveCard(Card("8", "다이아몬드"))
        dealer.receiveCard(Card("8", "하트"))

        // when
        BlackjackGame.updateGameResult(players, dealer)

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
}
