package model

class ScoreCalculator(private val hand: Hand) {
    private fun countAce(): Int {
        return hand.handCards.count { it.cardRank == CardRank.ACE }
    }

    fun calculateTotalCardScore(): Int {
        var score = hand.getScore()
        var aceCount = countAce()
        while (aceCount > DEFAULT_ZERO && score + ACE_PLUS_VALUE <= GameResultDecider.BLACKJACK_SCORE) {
            score += ACE_PLUS_VALUE
            aceCount--
        }
        return score
    }

    companion object {
        private const val DEFAULT_ZERO = 0
        private const val ACE_PLUS_VALUE = 10
    }
}
