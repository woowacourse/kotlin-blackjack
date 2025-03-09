package blackjack.model

import blackjack.model.ResultType.Companion.BUST_NUMBER

class Dealer(
    name: String = DEALER_NAME,
) : Participant(name) {
    fun isAvailDrawCard() = ScoreCalculator.sum(cards) < DEALER_DRAW_CARD_MINIMUM_SCORE

    override fun isBust(): Boolean = ScoreCalculator.sum(cards) > BUST_NUMBER

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_DRAW_CARD_MINIMUM_SCORE = 17
    }
}
