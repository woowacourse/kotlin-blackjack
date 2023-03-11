package domain

import domain.card.Card

class Dealer(val name: String = DEALER_NAME, val cards: Cards) {
    fun isOverSumCondition(): Boolean = (cards.score.isOver(Score.valueOf(SUM_CONDITION)))

    fun addCard(card: Card) = cards.addCard(card)

    companion object {
        private const val SUM_CONDITION = 16
        private const val DEALER_NAME = "딜러"
    }
}