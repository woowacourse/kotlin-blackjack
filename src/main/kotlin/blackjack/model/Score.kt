package blackjack.model

class Score(private val cards: Set<Card>) {
    var point: Int = DEFAULT_POINT
        private set

    init {
        calculateScore()
    }

    private fun calculateScore() {
        var score = cards.sumOf { card -> card.number.value }
        var bonusScore = calculateBonusScore(cards, score)

        val numberOfAce = cards.count { card -> card.number == CardNumber.ACE }
        repeat(numberOfAce) {
            if (score + bonusScore <= BONUS_SCORE_CRITERIA) {
                score += BONUS_SCORE
                bonusScore += BONUS_SCORE
            }
        }
        point = score + bonusScore
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
        private const val DEFAULT_POINT = 0
        private const val BONUS_SCORE_CRITERIA = 11
        private const val BONUS_SCORE = 10
    }
}
