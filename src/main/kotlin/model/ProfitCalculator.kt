package model

class ProfitCalculator {
    private fun getProfitRate(playerResult: PlayerResult): Float {
        return when (playerResult.result) {
            GameResult.WIN -> GameResult.WIN.profitRate
            GameResult.LOSE -> GameResult.LOSE.profitRate
            GameResult.PUSH -> GameResult.PUSH.profitRate
            GameResult.BLACKJACK -> GameResult.BLACKJACK.profitRate
        }
    }

    private fun calculateProfitRates(playerResults: List<PlayerResult>): Map<Player, Float> {
        return playerResults.associate { playerResult ->
            playerResult.player to getProfitRate(playerResult)
        }
    }

    fun calculateFinalProfits(
        playerResults: List<PlayerResult>,
        bettingManager: BettingManager,
        players: Players,
    ): Map<Player, Int> {
        val profitRates = calculateProfitRates(playerResults)
        return players.associateWith { player ->
            val baseBet = bettingManager.getProfit(player)
            val multiplier = profitRates[player] ?: 0f
            (baseBet * multiplier).toInt()
        }
    }
}
