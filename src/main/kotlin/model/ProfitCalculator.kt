package model

class ProfitCalculator {
    private fun getProfitRate(playerResult: PlayerResult): Float {
        return playerResult.result.profitRate
    }

    fun calculateFinalProfits(
        playerResults: List<PlayerResult>,
        bettingManager: BettingManager,
        players: Players,
    ): Map<Player, Money> {

        return players.associateWith { player ->
            val baseBet = bettingManager.getProfit(player)
            val multiplier = getProfitRate(playerResults.first { it.player == player })
            baseBet.multiply(multiplier)
        }
    }
}
