package blackjack.domain.model.hand.strategy

import blackjack.domain.model.Score

class DealerStay : StayStrategy {
    override fun isStay(score: Score): Boolean = score.value >= DEALER_MIN_STAY_SCORE

    private companion object {
        const val DEALER_MIN_STAY_SCORE = 17
    }
}
