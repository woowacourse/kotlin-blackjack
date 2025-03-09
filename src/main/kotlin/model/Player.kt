package model

import model.GameResultDecider.Companion.BLACKJACK_SCORE

class Player(val name: String, private val playerCards: Cards) : Participant(playerCards) {
    init {
        require(name.isNotEmpty()) { PLAYER_BLANK_ERROR_MESSAGE }
    }

    fun getPlayerCardNames(): List<String> = playerCards.names()

    override fun turn(drawnCard: Card): Boolean {
        if (canHit()) {
            addCard(drawnCard)
            return false
        }
        return true
    }

    override fun canHit(): Boolean = currentScore() <= BLACKJACK_SCORE

    companion object {
        private const val PLAYER_BLANK_ERROR_MESSAGE = "[ERROR] 이름은 빈 값일 수 없습니다."
    }
}
