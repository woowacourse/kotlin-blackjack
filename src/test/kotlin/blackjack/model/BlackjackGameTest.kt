package blackjack.model

import blackjack.model.participant.Dealer
import blackjack.model.participant.Player
import blackjack.model.participant.Players
import blackjack.model.state.Hit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackGameTest {
    @Test
    fun `첫 턴에 딜러와 플레이어에게 2장씩 카드가 배분된다`() {
        val dealer = Dealer()
        val player = Player("모찌")
        val players = Players(listOf(player))
        val game = BlackjackGame(players, dealer)

        game.firstTurn()

        assertThat(dealer.state.hand.size).isEqualTo(2)
        assertThat(player.state.hand.size).isEqualTo(2)
    }

    @Test
    fun `딜러 턴에서 히트 가능한 경우 카드가 추가되어야 한다`() {
        val dealer = Dealer()
        val player = Player("모찌")
        val players = Players(listOf(player))
        val game = BlackjackGame(players, dealer)

        val result = game.dealerTurn()

        assertThat(result).isTrue()
    }

    @Test
    fun `playerTurn가 플레이어 상태를 업데이트한다`() {
        val dealer = Dealer()
        val player = Player("모찌")
        val players = Players(listOf(player))
        val game = BlackjackGame(players, dealer)

        val initialHandSize = player.state.hand.size

        game.playerTurn(player)
        val handSizeAfterHit = player.state.hand.size

        assertThat(handSizeAfterHit).isEqualTo(initialHandSize + 1)

        game.playerTurn(player)
        val stateAfterStop = player.state

        assertThat(stateAfterStop).isInstanceOf(Hit::class.java)
    }
}
