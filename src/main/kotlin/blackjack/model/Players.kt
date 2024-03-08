package blackjack.model

class Players(val players: List<Player>) {
    init {
        require(players.map { it.name }.toSet().size == players.size) { "플레이어 이름에 중복이 있습니다." }
        require(players.size in PLAYERS_COUNT_RANGE) {
            "플레이어의 수는 ${MIN_PLAYERS_COUNT}명 이상, ${MAX_PLAYERS_COUNT}명 이하여야 합니다."
        }
    }

    companion object {
        private const val MIN_PLAYERS_COUNT = 1
        private const val MAX_PLAYERS_COUNT = 6
        private val PLAYERS_COUNT_RANGE = MIN_PLAYERS_COUNT..MAX_PLAYERS_COUNT
    }
}
