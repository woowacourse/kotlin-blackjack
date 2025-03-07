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
        while (score > 21 && aceCount-- > 0) {
            score -= 10
        }
        return score
    }
}
