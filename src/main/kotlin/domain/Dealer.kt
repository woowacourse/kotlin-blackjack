package domain

import domain.card.Card

class Dealer(name: String = DEALER_NAME, cards: List<Card>) : Player(name, cards) {
    fun isOverSumCondition(): Boolean = (actualCardValueSum() > SUM_CONDITION)

    companion object {
        private const val SUM_CONDITION = 16
        private const val DEALER_NAME = "딜러"
    }
}
