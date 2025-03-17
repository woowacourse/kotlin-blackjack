package model

data class GameResults(
    private val profits: Map<Player, Money>
) {
    fun getPlayersProfit(): Map<Player, Money> = profits
}