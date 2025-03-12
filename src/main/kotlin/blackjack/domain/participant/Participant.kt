package blackjack.domain.participant

import blackjack.domain.Card
import blackjack.domain.Hand

abstract class Participant {
    val hand: Hand = Hand()

    abstract val hitThreshold: Int

    fun addCard(card: Card) {
        hand.addCard(card)
    }

    fun canHit(): Boolean {
        return hand.getTotalSum() <= hitThreshold
    }
}
