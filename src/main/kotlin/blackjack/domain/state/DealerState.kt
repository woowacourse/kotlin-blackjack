package blackjack.domain.state

import blackjack.domain.ScoreCalculator.BLACKJACK_SCORE
import blackjack.domain.calculateScore
import blackjack.domain.person.Dealer

enum class DealerState(override val isFinal: Boolean) : PersonState {
    HIT(false),
    BUST(true),
    STAY(true),
    ;

    companion object {
        private const val DEALER_ADDITIONAL_DRAW_BASE_SCORE = 16

        fun from(dealer: Dealer): DealerState {
            val score = dealer.calculateScore()
            return when {
                score > BLACKJACK_SCORE -> BUST
                score > DEALER_ADDITIONAL_DRAW_BASE_SCORE -> STAY
                else -> HIT
            }
        }
    }
}
