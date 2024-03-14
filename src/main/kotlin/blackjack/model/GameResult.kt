package blackjack.model

data class GameResult(
    private val dealerResult: Map<Result, Int>,
    private val playerResults: Map<Player, Result>,
) {
    fun getDealerResult(result: Result): Int {
        return dealerResult[result] ?: 0
    }

    fun getPlayerResult(player: Player): Result? {
        return playerResults[player]
    }

    fun getResultPlayers(): Set<Player> {
        return playerResults.keys
    }
}
