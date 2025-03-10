package model

class ScoreCalculator(private val hand: Hand) {
    private fun countAce(): Int {
        return hand.handCards.count { it.cardRank == CardRank.ACE }
    }

    private fun calculateWithoutAce(): Int {
        return hand.handCards.filter { it.cardRank != CardRank.ACE }
            .sumOf { it.cardRank.score }
    }

    fun calculateTotalCardScore(): Int {
        var score = calculateWithoutAce() + (countAce() * ACE_HIGH_VALUE)
        var aceCount = countAce()

        while (score > GameResultDecider.BLACKJACK_SCORE && aceCount > DEFAULT_ZERO) {
            score -= ACE_MINUS_VALUE
            aceCount--
        }

        return score
    }

    companion object {
        private const val DEFAULT_ZERO = 0
        private const val ACE_MINUS_VALUE = 10
        private const val ACE_HIGH_VALUE = 11
    }
}
