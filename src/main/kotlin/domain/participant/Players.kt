package domain.participant

class Players(val list: List<Player>) {
    init {
        check(list.size in MINIMUM_PLAYER_COUNT..MAXIMUM_PLAYER_COUNT) {
            "[ERROR] 플레이어는 최소 1명이상 8명이하여야 합니다."
        }
    }

    companion object {
        private const val MINIMUM_PLAYER_COUNT = 1
        private const val MAXIMUM_PLAYER_COUNT = 8
    }
}
