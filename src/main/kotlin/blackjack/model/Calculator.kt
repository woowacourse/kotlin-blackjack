package blackjack.model

object Calculator {
    private const val BONUS_SCORE_CRITERIA = 11
    private const val BONUS_SCORE = 10

    fun calculateScore(cards: Set<Card>): Int {
        val score = cards.sumOf { card -> card.number.value }
        val bonusScore = calculateBonusScore(cards, score)
        return score + bonusScore
    }

    private fun calculateBonusScore(
        cards: Set<Card>,
        score: Int,
    ): Int {
        var bonusScore = 0
        var totalScore = score
        val numberOfAce = cards.count { card -> card.number == CardNumber.ACE }
        repeat(numberOfAce) {
            if (totalScore <= BONUS_SCORE_CRITERIA) {
                totalScore += BONUS_SCORE
                bonusScore += BONUS_SCORE
            }
        }
        return bonusScore
    }
}
