package model

data class Players(private val players: List<Player>) {
    fun toList(): List<Player> = players.toList()
    fun toNames(): Names = Names(players.map { it.name })
}
