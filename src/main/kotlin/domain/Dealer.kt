package domain

import domain.card.Card

class Dealer private constructor(
    name: String = DEALER_NAME,
    deck: Deck,
) : Player(name, deck) {
    fun isOverSumCondition(): Boolean = (calculateCardValueSum() > SUM_CONDITION)

    companion object {
        private const val SUM_CONDITION = 16
        private const val DEALER_NAME = "딜러"
        fun create(cards: List<Card>) = Dealer(deck = Deck(cards.toMutableList()))
    }
}
