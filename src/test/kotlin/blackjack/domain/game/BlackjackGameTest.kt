package blackjack.domain.game

import blackjack.domain.betting.BettingMoney
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class BlackjackGameTest {
    @Test
    fun `BlackjackGame을 생성 후 게임을 실행한다`() {
        val participant = BlackjackParticipant.of(
            Dealer(),
            listOf("hatti"),
            ::getBettingMoney
        )

        assertDoesNotThrow {
            BlackjackGame().apply {
                startGame(participant, ::output)
                runPlayer(participant.players, ::isHit, ::output)
                runDealer(participant.dealer, ::output)
            }
        }
    }

    private fun getBettingMoney(name: String): BettingMoney = BettingMoney(1000)
    private fun isHit(name: String): Boolean = false
    private fun output(dealer: Dealer, players: List<Player>) = null
    private fun output(player: Player) = null
    private fun output(name: String) = null
}
