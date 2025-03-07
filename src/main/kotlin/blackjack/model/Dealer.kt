package blackjack.model

import blackjack.model.ResultType.Companion.BUST_NUMBER

class Dealer : Person() {
    val name = DEALER_NAME

    fun isMoreCard() = calculateTotalScore() < DEALER_MORE_CARD_MINIMUM

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_MORE_CARD_MINIMUM = 17
    }

    override fun isBust(): Boolean = super.calculateTotalScore() > BUST_NUMBER
}
