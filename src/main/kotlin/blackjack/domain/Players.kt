package blackjack.domain

class Players(private val players: List<Player>) {
    init {
        require(players.size in MIN_PLAYER_COUNT..MAX_PLAYER_COUNT) { PLAYER_COUNT_ERROR }
        require(players.isNotDuplicated()) { PLAYER_DUPLICATED_ERROR }
    }

    private fun List<Player>.isNotDuplicated(): Boolean = this.distinct().size == this.size
    fun haveInitialCards() = players.all { it.hasInitialCards() }
    fun toList(): List<Player> = players.toList()
    fun <V> associateWith(valueSelector: (Player) -> V): Map<Player, V> {
        return players.associateWith { valueSelector(it) }
    }

    companion object {
        private const val MIN_PLAYER_COUNT = 2
        private const val MAX_PLAYER_COUNT = 8
        private const val PLAYER_COUNT_ERROR = "플레이어의 수는 ${MIN_PLAYER_COUNT}명 이상 ${MAX_PLAYER_COUNT}명 이하여야 합니다."
        private const val PLAYER_DUPLICATED_ERROR = "중복된 플레이어가 존재합니다."

        fun create(playerNames: List<String>): Players {
            return Players(playerNames.map(::Player))
        }
    }
}
