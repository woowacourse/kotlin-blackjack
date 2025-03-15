package blackjack.model.domain.participant

import blackjack.model.domain.card.Card
import blackjack.model.domain.card.Hand

class Dealer(override val name: String = DEALER_NAME) : Participants() {
    override val hand: Hand = Hand(mutableListOf())

    override fun canHit(): Boolean {
        return sumCardNumber <= THRESHOLD
    }

    override fun showInitCards(): List<Card> {
        return listOf(hand.cards.first())
    }

    companion object {
        private const val DEALER_NAME: String = "딜러"
        const val THRESHOLD: Int = 16
    }
}
