package model.participants

data class Names(val names: List<Name>) : List<Name> by names {
    init {
        require(names.size < PLAYER_MAX) { PLAYER_COUNT_ERROR }
        require(names.distinct().size == names.size) { PLAYER_NAME_DUPLICATE }
    }
    companion object {
        private const val PLAYER_MAX = 8
        private const val PLAYER_COUNT_ERROR = "플레이어는 8명까지 가능합니다"
        private const val PLAYER_NAME_DUPLICATE = "플레이어들의 이름은 중복될 수 없습니다"
    }
}
