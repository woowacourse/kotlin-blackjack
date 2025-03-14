package model

class Players(private val players: List<Player>) : Iterable<Player> {
    init {
        require(players.isNotEmpty()) { PLAYERS_NUMBER_ERROR_MESSAGE }
    }

    override fun iterator(): Iterator<Player> = players.iterator()

    fun getPlayersNames(): List<String> = players.map { it.name }

    fun getPlayersCard(): List<List<Card>> = players.map { it.cards }

    fun getPlayersScores(): List<Int> = players.map { it.getTotalScore() }

    companion object {
        private const val PLAYERS_NUMBER_ERROR_MESSAGE = "플레이어의 수는 1명 이상이어야 합니다"
    }
}
