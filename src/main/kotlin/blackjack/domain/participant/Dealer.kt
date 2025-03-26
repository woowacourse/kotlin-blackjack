package blackjack.domain.participant

import blackjack.domain.card.Card

class Dealer : Participant() {
    override val hitThreshold: Int
        get() = DEALER_HIT_THRESHOLD

    override fun showInitialCards(): List<Card> {
        return hand.cards().take(DEALER_INITIAL_CARD_COUNT)
    }

    companion object {
        private const val DEALER_HIT_THRESHOLD = 16
        private const val DEALER_INITIAL_CARD_COUNT = 1
    }
}
