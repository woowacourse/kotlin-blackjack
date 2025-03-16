package blackjack.domain.participant

import blackjack.domain.DealerResult

class Dealer : Participant() {
    val dealerResult: DealerResult = DealerResult()

    override val hitThreshold: Int
        get() = DEALER_HIT_THRESHOLD

    companion object {
        private const val DEALER_HIT_THRESHOLD = 16
    }
}
