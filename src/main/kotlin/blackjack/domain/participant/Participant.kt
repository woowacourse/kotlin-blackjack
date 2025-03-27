package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.hand.Hand
import blackjack.domain.hand.HandStatus

abstract class Participant {
    val hand: Hand = Hand()

    fun getStatus(): HandStatus = HandStatus.from(hand)

    abstract val hitThreshold: Int

    abstract fun showInitialCards(): List<Card>

    fun addCard(card: Card) {
        hand.addCard(card)
    }

    fun canHit(): Boolean {
        return hand.canHit(hitThreshold)
    }

    fun getTotalSum(): Int {
        return hand.totalSum()
    }
}
