package model

class ScoreCalculator(private val cards: Cards) {
    private fun calculateCardScore(): Int {
        return cards.allCards.sumOf { it.cardRank.score }
    }

    private fun countAce(cards: Cards): Int {
        return cards.allCards.count { it.cardRank == CardRank.ACE }
    }

    private fun calculateWithoutAce(): Int = calculateCardScore() - countAce(cards)

    fun calculateTotalCardScore(): Int {
        var score = calculateWithoutAce() + countAce(cards)
        var aceCount = countAce(cards)
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
