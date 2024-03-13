package blackjack.model.role

class Dealer : Role(name = PlayerName(DEALER)) {
    val dealerDecisionCondition = { state.getCardHands().calculateScore() <= DEALER_MAX_SCORE_FOR_HIT }

    companion object {
        private const val DEALER = "딜러"
        private const val DEALER_MAX_SCORE_FOR_HIT = 16
    }
}
