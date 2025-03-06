package blackjack.domain.state

import blackjack.const.GameRule
import blackjack.domain.person.Dealer

enum class DealerState(override val isFinal: Boolean) : PersonState {
    FIRST_TURN(false),
    HIT(false),
    FINISH(true),
    ;

    companion object {
        fun from(dealer: Dealer): DealerState =
            when {
                dealer.cards().size < GameRule.FIRST_TURN_DRAW_AMOUNT -> FIRST_TURN
                dealer.score() > GameRule.DEALER_ADDITIONAL_DRAW_BASE_SCORE -> FINISH
                else -> HIT
            }
    }
}
