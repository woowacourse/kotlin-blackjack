package domain

class Players(val list: List<Player>) {
    init {
        check(list.size in MINIMUM_PLAYER_COUNT..MAXIMUM_PLAYER_COUNT) {
            "[ERROR] 플레이어는 최소 1명이상 8명이하여야 합니다."
        }
    }

    fun result(dealer: Participant): Map<GameResult, Int> {
        val result = GameResult.values().associateWith { INITIALIZE_TO_ZERO }.toMutableMap()
        list.forEach { player ->
            val playerResult = player.getGameResult(dealer)
            result[playerResult] = (result[playerResult] ?: INITIALIZE_TO_ZERO) + PLUS_ONE
        }
        return result
    }

    companion object {
        private const val MINIMUM_PLAYER_COUNT = 1
        private const val MAXIMUM_PLAYER_COUNT = 8
        private const val INITIALIZE_TO_ZERO = 0
        private const val PLUS_ONE = 1
    }
}
