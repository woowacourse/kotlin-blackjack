package model

class Players(private val players: List<Player>) : List<Player> by players {
    val names: List<String> = players.map { player -> player.name }
    val getPlayerCardNames: List<List<Pair<String, String>>>
        get() = players.map { player -> player.getPlayerCardNames }

    val scores: List<Int>
        get() = players.map { player -> player.currentScore() }

    init {
        require(players.isNotEmpty()) { PLAYERS_NUMBER_ERROR_MESSAGE }
    }

    companion object {
        private const val PLAYERS_NUMBER_ERROR_MESSAGE = "플레이어의 수는 1명 이상이어야 합니다"
    }
}
