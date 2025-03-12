package blackjack.domain.participant

import blackjack.domain.Result

class Dealer : Participant() {
    val result: Result = Result()

    override val hitThreshold: Int
        get() = DEALER_HIT_THRESHOLD

    companion object {
        const val DEALER_HIT_THRESHOLD = 16
    }
}
