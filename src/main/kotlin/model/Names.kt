package model

data class Names(val names: List<Name>) : List<Name> by names {
    init {
        require(names.distinct().size == names.size) { PLAYER_NAME_DUPLICATE }
    }
    companion object {
        private const val PLAYER_NAME_DUPLICATE = "플레이어들의 이름은 중복될 수 없습니다"
    }
}
