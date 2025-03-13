package blackjack.model.domain.participant

import blackjack.model.domain.card.Card
import blackjack.model.domain.card.Hand

abstract class Participants() {
    abstract val name: String
    abstract val hand: Hand

    val sumCardNumber: Int get() = hand.getSumNumber()
    val cardDeck get() = hand.cards.toList()

    fun receiveCard(cards: List<Card>) {
        hand.append(cards)
    }

    fun checkBust(): Boolean {
        return hand.isBust(sumCardNumber)
    }

    abstract fun canHit(): Boolean
}
