package model.participant

import model.card.Card
import model.card.CardName
import model.card.Cards
import model.result.ProfitCalculator.Companion.BLACKJACK_SCORE

class Player(val name: String, private val playerCards: Cards, val betAmount: Float) : Participant(playerCards) {
    val cardNames: List<CardName>
        get() = playerCards.names

    init {
        require(name.isNotEmpty()) { PLAYER_BLANK_ERROR_MESSAGE }
        require(betAmount > 0f) { PLAYER_AMOUNT_ERROR_MESSAGE }
    }

    override fun turn(drawnCard: Card): Boolean {
        if (canHit()) {
            addCard(drawnCard)
            return false
        }
        return true
    }

    override fun canHit(): Boolean = currentScore <= BLACKJACK_SCORE

    companion object {
        private const val PLAYER_BLANK_ERROR_MESSAGE = "[ERROR] 이름은 빈 값일 수 없습니다."
        private const val PLAYER_AMOUNT_ERROR_MESSAGE = "[ERROR] 0원 이하의 금액은 입력할 수 없습니다."
    }
}
