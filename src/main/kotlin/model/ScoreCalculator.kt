package model

import model.GameResultDecider.Companion.BLACKJACK_SCORE

class ScoreCalculator(private val cards: Cards) {
    private var totalCardScore: Int = cards.scores().sum()

    fun calculateTotalCardScore(): Int {
        val aceCount = cards.aceCount()
        val overScore = (totalCardScore - BLACKJACK_SCORE).coerceAtLeast(DEFAULT_ZERO)
        val decreasedScore = (overScore / ACE_MINUS_VALUE).coerceAtMost(aceCount) * ACE_MINUS_VALUE

        return totalCardScore - decreasedScore
    }

    companion object {
        private const val DEFAULT_ZERO = 0
        private const val ACE_MINUS_VALUE = 10
    }
}
