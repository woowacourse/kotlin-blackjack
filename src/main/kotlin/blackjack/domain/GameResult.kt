package blackjack.domain

import blackjack.domain.person.Dealer
import blackjack.domain.person.Player
import blackjack.domain.state.PersonState
import blackjack.domain.state.ResultState

class GameResult(dealer: Dealer, players: List<Player>) {
    val playerPayouts: Map<Player, Double>
    val dealerProfit: Double

    init {
        var totalDealerProfit = 0.0
        playerPayouts =
            players.associateWith { player ->
                val resultState = ResultState.calculateWin(player, dealer)
                val payout = calculatePayout(player, resultState)
                totalDealerProfit += calculateDealerProfit(player, resultState)
                payout
            }
        dealerProfit = totalDealerProfit
    }

    private fun calculatePayout(
        player: Player,
        resultState: ResultState,
    ): Double {
        val payout =
            when (resultState) {
                ResultState.WIN -> calculateWinPayout(player)
                ResultState.LOSE -> ZERO_PAYOUT
                ResultState.DRAW -> calculateDrawPayout(player)
            }

        return payout
    }

    private fun calculateWinPayout(player: Player): Double {
        if (player.gameState == PersonState.BLACKJACK) {
            return player.betAmount * BLACKJACK_PAYOUT_MULTIPLIER
        }

        return (player.betAmount * DRAW_PAYOUT_MULTIPLIER)
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
    }
}
