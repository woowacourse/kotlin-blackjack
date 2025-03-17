package blackjack.domain.participant

import blackjack.domain.DealerResult
import blackjack.domain.card.Card

class Dealer : Participant() {
    val dealerResult: DealerResult = DealerResult()

    override val hitThreshold: Int
        get() = DEALER_HIT_THRESHOLD

    override fun showInitialCards(): List<Card> {
        return hand.getCards().take(DEALER_INITIAL_CARD_COUNT)
    }

    companion object {
        private const val DEALER_HIT_THRESHOLD = 16
        private const val DEALER_INITIAL_CARD_COUNT = 1
    }
}
