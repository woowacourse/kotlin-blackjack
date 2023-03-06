package model

data class Players(val players: List<Player>) : List<Player> by players {
    fun getGameResult(dealer: Participant): Map<Name, Result> {
        val result = mutableMapOf<Name, Result>()
        players.forEach { player ->
            result[player.name] = player.getGameResult(dealer)
        }
        return result
    }
}
