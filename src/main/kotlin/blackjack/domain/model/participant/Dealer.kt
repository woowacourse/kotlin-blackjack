package blackjack.domain.model.participant

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Hand

class Dealer(override val name: String = DEALER_NAME) : Participants() {
    override val hand: Hand = Hand(mutableListOf())

    override fun canHit(): Boolean {
        return sumCardNumber <= THRESHOLD
    }

    override fun getInitCard(): List<Card> {
        return hand.cards.take(1)
    }

    companion object {
        private const val DEALER_NAME: String = "딜러"
        private const val THRESHOLD: Int = 16
    }
}
