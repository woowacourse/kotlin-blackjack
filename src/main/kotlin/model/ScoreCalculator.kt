package model

class ScoreCalculator(private val cards: Cards) {
    private fun calculateCardScore(): Int {
        return cards.getCardScores().sum()
    }

    private fun calculateAceScore(): Int = cards.aceCount()

    private fun calculateWithoutAce(): Int = calculateCardScore() - calculateAceScore()

    fun calculateTotalCardScore(): Int {
        var score = calculateWithoutAce() + cards.aceCount()
        var aceCount = cards.aceCount()
        while (score > GameResultDecider.BLACKJACK_SCORE && aceCount-- > DEFAULT_ZERO) {
            score -= ACE_MINUS_VALUE
        }
        return score
    }

    companion object {
        private const val DEFAULT_ZERO = 0
        private const val ACE_MINUS_VALUE = 10
    }
}
