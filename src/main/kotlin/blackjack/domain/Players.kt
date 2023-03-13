package blackjack.domain

class Players(private val players: Set<Player>) {
    init {
        require(players.size in MIN_PLAYER_COUNT..MAX_PLAYER_COUNT) { PLAYER_COUNT_ERROR }
    }

    constructor(players: List<Player>) : this(players.toSet())

    fun areFinished() = players.all { it.isFinished() }
    fun toList(): List<Player> = players.toList()
    fun <V> associateWith(valueSelector: (Player) -> V): Map<Player, V> {
        return players.associateWith { valueSelector(it) }
    }
    fun forEach(action: (Player) -> Unit) {
        for (player in players) action(player)
    }

    companion object {
        private const val MIN_PLAYER_COUNT = 2
        private const val MAX_PLAYER_COUNT = 8
        private const val PLAYER_COUNT_ERROR = "플레이어의 수는 ${MIN_PLAYER_COUNT}명 이상 ${MAX_PLAYER_COUNT}명 이하여야 합니다."

        fun create(playerNames: List<String>): Players {
            return Players(playerNames.map(::Player).toSet())
        }
    }
}
