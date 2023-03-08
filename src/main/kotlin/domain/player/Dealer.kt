package domain.player

import domain.card.Card
import domain.card.Hand

class Dealer private constructor(
    name: String = DEALER_NAME,
    hand: Hand,
) : Player(name, hand) {

    fun isOverSumCondition(): Boolean = (calculateCardValueSum() > SUM_CONDITION)

    companion object {
        private const val SUM_CONDITION = 16
        private const val DEALER_NAME = "딜러"
        fun create(cards: List<Card>) = Dealer(hand = Hand(cards.toMutableList()))
    }
}
