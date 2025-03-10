package model

import model.GameResultDecider.Companion.BLACKJACK_SCORE

class ScoreCalculator(private val cards: Cards) {
    private var totalCardScore: Int = cards.scores.sum()

    fun calculateTotalCardScore(): Int {
        var aceCount = cards.aceCount
        while (totalCardScore > BLACKJACK_SCORE && aceCount-- > DEFAULT_ZERO) {
            totalCardScore -= ACE_MINUS_VALUE
        }

        return totalCardScore
    }

    companion object {
        private const val DEFAULT_ZERO = 0
        private const val ACE_MINUS_VALUE = 10
    }
}
