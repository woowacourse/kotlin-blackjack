package blackjack.model.domain.participant

import blackjack.model.domain.card.Card
import blackjack.model.domain.card.Hand
import blackjack.model.domain.participant.ParticipantStatus.Companion.isBust

abstract class Participants() {
    abstract val name: String
    protected abstract val hand: Hand
    abstract var status: ParticipantStatus
        protected set

    val sumCardNumber: Int get() = hand.getSumNumber()
    val cardDeck get() = hand.cards.toList()

    fun receiveCard(card: Card) {
        hand.append(card)
    }

    fun checkBust() {
        status = isBust(sumCardNumber)
    }

    abstract fun canHit(): Boolean
}
