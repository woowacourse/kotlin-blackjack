package blackjack.model.domain.participant

import blackjack.model.domain.GameResult
import blackjack.model.domain.GameResult.Companion.isBust
import blackjack.model.domain.card.Card
import blackjack.model.domain.card.Hand

abstract class Participants() {
    abstract val name: String
    protected abstract val hand: Hand
    abstract var status: GameResult
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
