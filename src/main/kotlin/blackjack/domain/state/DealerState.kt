package blackjack.domain.state

import blackjack.const.GameRule
import blackjack.domain.ScoreCalculator
import blackjack.domain.person.Dealer

enum class DealerState(override val isFinal: Boolean) : PersonState {
    FIRST_TURN(false),
    HIT(false),
    FINISH(true),
    ;

    companion object {
        fun from(dealer: Dealer): DealerState {
            val cards = dealer.cards()
            return when {
                cards.size < GameRule.FIRST_TURN_DRAW_AMOUNT -> FIRST_TURN
                ScoreCalculator.calculate(cards) > GameRule.DEALER_ADDITIONAL_DRAW_BASE_SCORE -> FINISH
                else -> HIT
            }
        }
    }
}
