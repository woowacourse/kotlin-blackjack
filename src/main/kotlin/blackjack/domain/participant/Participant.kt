package blackjack.domain.participant

import blackjack.domain.Card
import blackjack.domain.Hand
import blackjack.domain.Result

abstract class Participant {
    val hand: Hand = Hand()
    val result: Result = Result()

    val totalSum: Int
        get() = hand.getCardSum()

    abstract val hitThreshold: Int

    fun addCard(card: Card) {
        hand.addCard(card)
    }

    fun canHit(): Boolean {
        return totalSum <= hitThreshold
    }
}
