package blackjack.domain.model.participant

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Hand

abstract class Participants() {
    abstract val name: String
    abstract val hand: Hand

    val sumCardNumber: Int get() = hand.getSumNumber()
    val cardDeck get() = hand.cards.toList()

    fun receiveCard(card: Card) {
        hand.append(card)
    }

    abstract fun canHit(): Boolean

    abstract fun getInitCard(): List<Card>
}
