package model

class Player(val name: String, val cards: Cards) {
    init {
        require(name.isNotEmpty()) { PLAYER_BLANK_ERROR_MESSAGE }
        require(cards.getCardsCount() == 2) { "[ERROR]" }
    }

    companion object {
        private const val PLAYER_BLANK_ERROR_MESSAGE = "[ERROR] 이름은 빈 값일 수 없습니다."
    }
}
