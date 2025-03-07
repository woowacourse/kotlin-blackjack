package model

class Player(val name: String, val playerCards: Cards) : Participant(playerCards) {
    init {
        require(name.isNotEmpty()) { PLAYER_BLANK_ERROR_MESSAGE }
    }

    override fun turn(cards: Cards): Boolean {
        if (isHit()) {
            val drawnCard = drawCard(cards.allCards)
            addCard(drawnCard)
            return false
        }
        return true
    }

    override fun isHit(): Boolean = getScore() <= 21

    companion object {
        private const val PLAYER_BLANK_ERROR_MESSAGE = "[ERROR] 이름은 빈 값일 수 없습니다."
    }
}
