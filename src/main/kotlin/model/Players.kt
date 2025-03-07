package model

class Players(private val players: List<Player>) : Iterable<Player> {
    init {
        require(players.isNotEmpty()) { "플레이어의 수는 1명 이상이어야 합니다" }
    }

    override fun iterator(): Iterator<Player> = players.iterator()

    fun getPlayerCardNames(): List<List<String>> {
        return players.map { player ->
            player.getPlayerCardNames()
        }
    }

    fun getPlayersNames(): List<String> = players.map { player -> player.name }

    fun getPlayersScores(): List<Int> = players.map { player -> player.getScore() }
}
