package blackjack.domain.state

import blackjack.const.GameRule
import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Participant

enum class GameState {
    FIRST_TURN,
    HIT,
    STAY,
    BUST,
    BLACKJACK,
    ;

    companion object {
        fun from(participant: Participant): GameState {
            return when {
                participant.score() > GameRule.BLACKJACK_SCORE -> BUST
                participant is Dealer && participant.score() > GameRule.DEALER_ADDITIONAL_DRAW_BASE_SCORE -> STAY
                participant.score() == GameRule.BLACKJACK_SCORE && participant.hand.size == GameRule.FIRST_TURN_DRAW_AMOUNT -> BLACKJACK
                else -> HIT
            }
        }
    }
}
