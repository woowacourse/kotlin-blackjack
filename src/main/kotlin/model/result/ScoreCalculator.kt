package model.result

import model.card.Cards
import model.result.GameResultDecider.Companion.BLACKJACK_SCORE

class ScoreCalculator(private val cards: Cards) {
    val initialTotalCardScore = cards.scores.sum()

    fun calculateTotalCardScore(): Int {
        val aceCount = cards.aceCount

        return if (initialTotalCardScore > BLACKJACK_SCORE && aceCount > DEFAULT_ZERO) {
            adjustAceScore(initialTotalCardScore, aceCount)
        } else {
            initialTotalCardScore
        }
    }

    private fun adjustAceScore(
        initialTotalCardScore: Int,
        aceCount: Int,
    ) =
        initialTotalCardScore - ACE_MINUS_VALUE * aceCount.coerceAtMost((initialTotalCardScore - BLACKJACK_SCORE) / ACE_MINUS_VALUE + 1)

    companion object {
        private const val DEFAULT_ZERO = 0
        private const val ACE_MINUS_VALUE = 10
    }
}
