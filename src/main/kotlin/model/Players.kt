package model

class Players(private val players: List<Player>) : List<Player> by players {
    init {
        require(players.isNotEmpty()) { PLAYERS_NUMBER_ERROR_MESSAGE }
    }

    fun getPlayerCardNames(): List<List<String>> {
        return players.map { player ->
            player.getPlayerCardNames()
        }
    }

    fun names(): List<String> = players.map { player -> player.name }

    fun scores(): List<Int> = players.map { player -> player.currentScore() }

    companion object {
        private const val PLAYERS_NUMBER_ERROR_MESSAGE = "플레이어의 수는 1명 이상이어야 합니다"
    }
}
