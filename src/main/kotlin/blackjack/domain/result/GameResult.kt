package blackjack.domain.result

import blackjack.domain.person.Dealer
import blackjack.domain.person.Player
import blackjack.domain.state.PersonState
import blackjack.domain.state.ResultState

class GameResult(dealer: Dealer, players: List<Player>) {
    val playerPayouts: Map<Player, Profit>
    val dealerProfit: Profit

    init {
        playerPayouts = players.associateWith { player -> calculatePlayerPayout(dealer, player) }
        dealerProfit = calculateDealerProfit()
    }

    private fun calculatePlayerPayout(
        dealer: Dealer,
        player: Player,
    ): Profit {
        val resultState = ResultState.calculateWin(player, dealer)
        val profit =
            when (resultState) {
                ResultState.WIN -> calculateWinPayout(player)
                ResultState.LOSE -> calculateLosePayout(player)
                ResultState.DRAW -> calculateDrawPayout(player)
            }

        return Profit(profit)
    }

    private fun calculateWinPayout(player: Player): Double {
        if (player.gameState == PersonState.BLACKJACK) {
            return player.betAmount * BLACKJACK_PAYOUT_MULTIPLIER
        }
        return player.betAmount * WINNING_PAYOUT_MULTIPLIER
    }

    private fun calculateLosePayout(player: Player): Double {
        return (player.betAmount * LOSING_PAYOUT_MULTIPLIER).toDouble()
    }

    private fun calculateDrawPayout(player: Player): Double {
        return player.betAmount * ZERO_PAYOUT
    }

    private fun calculateDealerProfit(): Profit {
        val profit = playerPayouts.values.sumOf { it.value } * LOSING_PAYOUT_MULTIPLIER
        val result = if (profit == -ZERO_PAYOUT) ZERO_PAYOUT else profit
        return Profit(result)
    }

    companion object {
        private const val BLACKJACK_PAYOUT_MULTIPLIER = 1.5
        private const val WINNING_PAYOUT_MULTIPLIER = 1.0
        private const val ZERO_PAYOUT = 0.0
        private const val LOSING_PAYOUT_MULTIPLIER = -1
    }
}
