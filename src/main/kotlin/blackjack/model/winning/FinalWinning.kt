package blackjack.model.winning

import blackjack.model.playing.participants.Dealer
import blackjack.model.playing.participants.player.Player
import blackjack.model.playing.participants.player.PlayerName
import blackjack.model.playing.participants.player.Players

class FinalWinning(val dealerWinning: DealerWinning, val playerWinning: PlayersWinning) {
    fun getProfit(
        dealer: Dealer,
        players: Players,
    ): Map<PlayerName, Double> {
        val profit = mutableMapOf<PlayerName, Double>()
        var dealerProfit = 0.0

        players.players.forEach { player ->
            val playerProfit = calculatePlayerProfit(player, profit)
            dealerProfit -= playerProfit
        }
        profit[dealer.name] = dealerProfit
        return profit
    }

    private fun calculatePlayerProfit(
        player: Player,
        profit: MutableMap<PlayerName, Double>,
    ): Double {
        var playerProfit = 0.0
        playerWinning.result.forEach { (playerName, winningResult) ->
            if (player.name == playerName) {
                playerProfit = player.calculateProfit(winningResult)
            }
        }
        profit[player.name] = playerProfit
        return playerProfit
    }
}
