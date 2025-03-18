package model

class ProfitCalculator {
    fun calculatePlayerProfits(
        playerResults: List<PlayerResult>,
        bettingManager: BettingManager,
    ): GameResults {
        val updatedResults =
            playerResults.map { playerResult ->
                val baseBet = bettingManager.getBetAmount(playerResult.player)
                val profit = baseBet.multiply(playerResult.outcome.result.profitRate)

                playerResult.copy(outcome = PlayerOutcome(playerResult.outcome.result, profit))
            }
        return GameResults(updatedResults, Money(0))
    }

    fun dealerProfit(gameResults: GameResults): Money {
        return Money(-gameResults.getPlayersProfit().values.sumOf { it.amount })
    }
}
