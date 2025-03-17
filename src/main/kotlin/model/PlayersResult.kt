package model

data class PlayersResult(
    private val profits: Map<Player, Money>
) {
    fun getPlayersProfit(): Map<Player, Money> = profits
}