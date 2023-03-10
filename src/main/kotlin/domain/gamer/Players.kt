package domain.gamer

class Players(private val players: List<Player>) {
    init {
        require(players.size <= MAX_PLAYER_COUNT) { println(ERROR_OVER_MAX_PLAYER_COUNT) }
    }

    fun getPlayers(): List<Player> {
        return players.toList()
    }

    companion object {
        private const val ERROR_OVER_MAX_PLAYER_COUNT = "게임 인원은 8명까지 가능합니다"
        private const val MAX_PLAYER_COUNT = 8
    }
}
