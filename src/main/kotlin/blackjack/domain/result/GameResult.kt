package blackjack.domain.result

import blackjack.domain.person.Dealer
import blackjack.domain.person.Player
import blackjack.domain.state.PersonState
import blackjack.domain.state.ResultState

class GameResult(dealer: Dealer, players: List<Player>) {
    val playerPayouts: Map<Player, Double>
    val dealerProfit: Double

    init {
        var totalDealerProfit = ZERO_PAYOUT
        playerPayouts =
            players.associateWith { player ->
                val resultState = ResultState.calculateWin(player, dealer)
                totalDealerProfit += calculateDealerProfit(player, resultState)
                calculatePlayerPayout(player, resultState)
            }
        dealerProfit = totalDealerProfit
    }

    private fun calculatePlayerPayout(
        player: Player,
        resultState: ResultState,
    ): Double {
        return when (resultState) {
            ResultState.WIN -> calculateWinPayout(player)
            ResultState.LOSE -> calculateLosePayout(player)
            ResultState.DRAW -> calculateDrawPayout(player)
        }
    }

    private fun calculateWinPayout(player: Player): Double {
        if (player.gameState == PersonState.BLACKJACK) {
            return player.betAmount * BLACKJACK_PAYOUT_MULTIPLIER
        }
        return player.betAmount * DRAW_PAYOUT_MULTIPLIER
    }

    private fun calculateLosePayout(player: Player): Double {
        return player.betAmount.toDouble() * LOSING_PAYOUT_MULTIPLIER
    }

    private fun calculateDrawPayout(player: Player): Double {
        return player.betAmount * DRAW_PAYOUT_MULTIPLIER
    }

    private fun calculateDealerProfit(
        player: Player,
        resultState: ResultState,
    ): Double {
        if (resultState == ResultState.LOSE) {
            return player.betAmount.toDouble()
        }
        return ZERO_PAYOUT
    }

    companion object {
        private const val BLACKJACK_PAYOUT_MULTIPLIER = 1.5
        private const val DRAW_PAYOUT_MULTIPLIER = 1.0
        private const val ZERO_PAYOUT = 0.0
        private const val LOSING_PAYOUT_MULTIPLIER = -1
    }
}
