package blackjack.domain.person

import blackjack.domain.BetAmount
import blackjack.domain.result.Profit
import blackjack.domain.state.PersonState
import blackjack.domain.state.ResultState

class PlayerBetInfo(
    val player: Player,
    private val betAmount: BetAmount,
) {
    fun calculatePlayerPayout(dealer: Dealer): Profit {
        val resultState = ResultState.calculateWin(player, dealer)
        val profit =
            when (resultState) {
                ResultState.WIN -> calculateWinPayout()
                ResultState.LOSE -> calculateLosePayout()
                ResultState.DRAW -> calculateDrawPayout()
            }

        return Profit(profit)
    }

    private fun calculateWinPayout(): Double {
        if (player.gameState == PersonState.BLACKJACK) {
            return betAmount * BLACKJACK_PAYOUT_MULTIPLIER
        }
        return betAmount * WINNING_PAYOUT_MULTIPLIER
    }

    private fun calculateLosePayout(): Double {
        return (betAmount * LOSING_PAYOUT_MULTIPLIER).toDouble()
    }

    private fun calculateDrawPayout(): Double {
        return betAmount * DRAW_PAYOUT_MULTIPLIER
    }

    companion object {
        private const val BLACKJACK_PAYOUT_MULTIPLIER = 1.5
        private const val WINNING_PAYOUT_MULTIPLIER = 1.0
        private const val DRAW_PAYOUT_MULTIPLIER = 0.0
        private const val LOSING_PAYOUT_MULTIPLIER = -1
    }
}
