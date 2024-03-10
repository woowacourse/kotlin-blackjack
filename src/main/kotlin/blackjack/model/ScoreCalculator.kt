package blackjack.model

object ScoreCalculator {
    private const val BONUS_SCORE_CRITERIA = 11
    private const val BONUS_SCORE = 10

    fun calculateScore(cards: Set<Card>): Int {
        var score = cards.sumOf { card -> card.number.value }
        score = calculateBonusScore(cards, score)
        return score
    }

    private fun calculateBonusScore(
        cards: Set<Card>,
        score: Int,
    ): Int {
        var totalScore = score
        val numberOfAce = cards.count { card -> card.number == CardNumber.ACE }
        repeat(numberOfAce) {
            if (totalScore <= BONUS_SCORE_CRITERIA) totalScore += BONUS_SCORE
        }
        return totalScore
    }
}
