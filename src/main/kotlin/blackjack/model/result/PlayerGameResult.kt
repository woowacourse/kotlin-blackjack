package blackjack.model.result

import blackjack.model.participant.Dealer
import blackjack.model.participant.Player
import blackjack.model.state.Blackjack
import blackjack.model.state.Bust
import blackjack.model.state.State
import blackjack.model.state.Stay

class PlayerGameResult(
    private val dealer: Dealer,
    private val player: Player,
) {
    fun playerProfitRate(): Float {
        val dealerState = dealer.state
        val playerState = player.state

        if (dealerState is Blackjack && playerState is Blackjack) return GameResult.PUSH.profit
        if (dealerState is Blackjack && playerState is Stay) return GameResult.LOSE.profit
        if (dealerState is Bust && playerState is Stay) return GameResult.WIN.profit
        if (dealerState is Stay && playerState is Stay) return getStayResult(dealerState, playerState)
        return playerState.profit()
    }

    private fun getStayResult(
        dealerState: State,
        playerState: State,
    ): Float {
        val dealerScore = dealerState.hand.score()
        val playerScore = playerState.hand.score()

        return when {
            dealerScore > playerScore -> GameResult.LOSE.profit
            dealerScore == playerScore -> GameResult.PUSH.profit
            else -> GameResult.WIN.profit
        }
    }
}
