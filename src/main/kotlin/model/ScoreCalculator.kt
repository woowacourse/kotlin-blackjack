package model

import model.GameResult.Companion.BLACKJACK_SCORE

class ScoreCalculator(private val hand: Hand) {
    fun calculateTotalCardScore(): Int {
        val baseScore = hand.getScore()
        return if (hand.isAceExist() && baseScore + ACE_PLUS_VALUE <= BLACKJACK_SCORE) {
            baseScore + ACE_PLUS_VALUE
        } else {
            baseScore
        }
    }

    companion object {
        private const val ACE_PLUS_VALUE = 10
    }
}
