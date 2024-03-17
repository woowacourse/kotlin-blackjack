package blackjack.model

class Score(private val cards: Set<Card>) {
    var point: Int = calculateScore()
        private set

    private fun calculateScore(): Int {
        var score = cards.sumOf { card -> card.number.value }
        var bonusScore = calculateBonusScore(cards, score)

        val numberOfAce = cards.count { card -> card.number == CardNumber.ACE }
        repeat(numberOfAce) {
            if (score + bonusScore <= BONUS_SCORE_CRITERIA) {
                score += BONUS_SCORE
                bonusScore += BONUS_SCORE
            }
        }
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

    companion object {
        private const val BONUS_SCORE_CRITERIA = 11
        private const val BONUS_SCORE = 10
    }
}
