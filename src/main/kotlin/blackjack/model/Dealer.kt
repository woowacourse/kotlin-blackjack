package blackjack.model

import blackjack.model.ResultType.Companion.BUST_NUMBER

class Dealer : Participant() {
    val name = DEALER_NAME

    fun isAvailDrawCard() = calculateTotalScore() < DEALER_DRAW_CARD_MINIMUM_SCORE

    override fun isBust(): Boolean = super.calculateTotalScore() > BUST_NUMBER

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_DRAW_CARD_MINIMUM_SCORE = 17
    }
}
