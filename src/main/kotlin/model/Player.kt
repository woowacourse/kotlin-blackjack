package model

data class Player(val name: String, val cards: Cards) {
    init {
        require(name.isNotEmpty()) { PLAYER_BLANK_ERROR_MESSAGE }
    }

    companion object {
        private const val PLAYER_BLANK_ERROR_MESSAGE = "[ERROR] 이름은 빈 값일 수 없습니다."
    }
}
