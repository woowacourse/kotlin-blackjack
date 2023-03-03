package model

data class Names(val names: List<Name>) : List<Name> by names {
    init {
        require(names.size < PLAYER_MAX) { PLAYER_COUNT_ERROR }
    }
    companion object {
        private const val PLAYER_MAX = 8
        private const val PLAYER_COUNT_ERROR = "플레이어는 8명까지 가능합니다"
    }
}
