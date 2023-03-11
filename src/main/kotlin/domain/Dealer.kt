package domain

import domain.card.Card

class Dealer(val name: String = DEALER_NAME, val cards: Cards) {

    fun getScore(): Score = cards.score
    fun isHit(): Boolean = (!cards.isOver(SUM_CONDITION))

    fun isBlackJack(): Boolean = cards.isBlackJack()

    fun addCard(card: Card) = cards.addCard(card)

    companion object {
        private const val SUM_CONDITION = 16
        private const val DEALER_NAME = "딜러"
    }
}
