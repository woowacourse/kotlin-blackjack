package model

class ScoreCalculator(private val cards: Cards) {
    private fun calculateCardScore(): Int {
        return cards.getCardScores().sum()
    }

    private fun calculateAceScore(): Int = cards.aceCount()

    private fun calculateWithoutAce(): Int = calculateCardScore() - calculateAceScore()

    fun totalCardScore(): Int {
        val withoutAceScore = calculateWithoutAce()
        var aceCount = cards.aceCount()
        val defaultAce = 1
        var containAceScore = withoutAceScore + defaultAce * aceCount

        while (containAceScore >= 21 && aceCount > 0) {
            containAceScore -= 10
            aceCount--
        }
        return containAceScore
    }
}
