package model

class ScoreCalculator(private val hand: Hand) {
    fun calculateTotalCardScore(): Int {
        val baseScore = hand.getScore()
        return if (hand.handCards.any { it.cardRank == CardRank.ACE } && baseScore + ACE_PLUS_VALUE <= GameResultDecider.BLACKJACK_SCORE) {
            baseScore + ACE_PLUS_VALUE
        } else {
            baseScore
        }
    }

    companion object {
        private const val DEFAULT_ZERO = 0
        private const val ACE_PLUS_VALUE = 10
    }
}
