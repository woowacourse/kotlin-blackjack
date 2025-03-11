package model

import model.GameResultDecider.Companion.BLACKJACK_SCORE

class ScoreCalculator(private val cards: Cards) {
    fun calculateTotalCardScore(): Int {
        val totalCardScore = cards.scores.sum()
        val aceCount = cards.aceCount

        return if (totalCardScore > BLACKJACK_SCORE && aceCount > DEFAULT_ZERO) {
            adjustAceScore(totalCardScore, aceCount)
        } else {
            totalCardScore
        }
    }

    private fun adjustAceScore(
        totalCardScore: Int,
        aceCount: Int,
    ) = totalCardScore - ACE_MINUS_VALUE * aceCount.coerceAtMost((totalCardScore - BLACKJACK_SCORE) / ACE_MINUS_VALUE + 1)

    companion object {
        private const val DEFAULT_ZERO = 0
        private const val ACE_MINUS_VALUE = 10
    }
}
