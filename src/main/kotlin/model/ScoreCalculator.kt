package model

class ScoreCalculator(private val cards: List<Card>) {
    fun calculateTotalScore(): Int {
        return cards.sumOf { card ->
            card.cardRank.score
        }
    }
}
