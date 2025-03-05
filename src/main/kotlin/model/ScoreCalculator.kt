package model

class ScoreCalculator(private val cards: Cards) {
    fun calculateTotalScore(): Int {
        return cards.allCards.sumOf { card ->
            card.cardRank.score
        }
    }

    private fun calculateAceScore(): Int = cards.aceCount()

    private fun calculateWithoutAce(): Int = calculateTotalScore() - calculateAceScore()

    // Ace가 한개만 있을 때
    fun totalScore(): Int {
        val withoutAceScore = calculateWithoutAce()
        var aceCount = cards.aceCount()
        val defaultAce = 1
        var containAceScore = withoutAceScore + defaultAce * aceCount

        while (containAceScore > 21 && aceCount > 0) {
            containAceScore -= 10
            aceCount--
        }
        return containAceScore
    }
}
