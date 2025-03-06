package model

class Player(val name: String, val playerCards: Cards) : Participant(playerCards) {
    init {
        require(name.isNotEmpty()) { PLAYER_BLANK_ERROR_MESSAGE }
    }

    override fun turn(allCards: Cards): Boolean {
        val score = getScore()
        if (score <= 21) {
            val drawnCard = allCards.drawCards(1)
            playerCards.addCards(drawnCard)
            return false
        }
        return true
    }

    companion object {
        private const val PLAYER_BLANK_ERROR_MESSAGE = "[ERROR] 이름은 빈 값일 수 없습니다."
    }
}
