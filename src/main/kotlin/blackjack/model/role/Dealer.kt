package blackjack.model.role

import blackjack.model.config.GameRule.DEALER_MAX_SCORE_FOR_HIT
import blackjack.model.config.GameRule.DEALER_NAME

class Dealer : Role(name = PlayerName(DEALER_NAME)) {
    val dealerDecisionCondition = { state.getCardHandScore() <= DEALER_MAX_SCORE_FOR_HIT }
}
