package domain

class PlayersNameAndBet(val list: List<NameAndBet>) {
    init {
        check(list.size in MINIMUM_PLAYER_COUNT..MAXIMUM_PLAYER_COUNT) { MAXIMUM_PLAYER_COUNT_ERROR }
    }

    companion object {
        private const val MINIMUM_PLAYER_COUNT = 1
        private const val MAXIMUM_PLAYER_COUNT = 8
        private const val MAXIMUM_PLAYER_COUNT_ERROR = "[ERROR] 플레이어는 최소 1명에서 최대 8명까지 허용됩니다."
    }
}
