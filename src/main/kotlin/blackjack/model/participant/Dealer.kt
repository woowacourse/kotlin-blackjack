package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.Hand

class Dealer(hand: Hand) {
    private val handCards: MutableList<Card> = hand.cards.toMutableList()
    val hand: Hand get() = Hand(handCards.toList())

    fun canHit(): Boolean {
        if (hand.isBust()) return false
        return hand.sumOptimized() < HIT_CONDITION
    }

    fun hit(card: Card) {
        if (canHit()) {
            handCards.add(card)
        }
    }

    companion object {
        const val HIT_CONDITION = 17
    }
}
