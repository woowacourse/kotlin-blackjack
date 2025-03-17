package model

data class GameResults(
    val playerResults: List<PlayerResult>,
    val dealerProfit: Money,
) {
    fun getPlayersProfit(): Map<Player, Money> {
        return playerResults.associate { it.player to it.outcome.profit }
    }
}
