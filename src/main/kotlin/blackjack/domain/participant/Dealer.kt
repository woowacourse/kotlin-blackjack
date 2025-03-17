package blackjack.domain.participant

import blackjack.domain.score.Score

class Dealer : Participant() {
    override val hitThreshold: Int
        get() = DEALER_HIT_THRESHOLD

    fun needsAdditionalCard(): Boolean {
        return Score(this) < DEALER_HIT_THRESHOLD
    }

    companion object {
        const val DEALER_HIT_THRESHOLD = 17
    }
}
