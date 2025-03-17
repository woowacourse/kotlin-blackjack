package model

class ProfitCalculator {
    private fun getProfitRate(playerResult: PlayerResult): Float {
        return playerResult.result.profitRate
    }

    fun calculatePlayerProfits(
        playerResults: List<PlayerResult>,
        bettingManager: BettingManager
    ): GameResults {
        val results = playerResults.associate { playerResult ->
            val baseBet = bettingManager.getBetAmount(playerResult.player)
            val profit = baseBet.multiply(getProfitRate(playerResult))
            playerResult.player to profit
        }
        return GameResults(results)
    }

    fun dealerProfit(playerProfits: GameResults): Money {
        return Money(-playerProfits.getPlayersProfit().values.sumOf { it.amount })
    }
//    fun dealerProfit(gameResults: GameResults): Int {
//        var rate = 0
//        gameResults.getPlayersProfit().values.forEach { money ->
//            rate -= money.amount
//        }
//        return rate
//    }
}
