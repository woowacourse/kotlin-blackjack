package blackjack.domain

class Hand(
    val cards: List<Card>,
    val money: Int,
) {
    val size = cards.size

    fun getTotalScore(): Int {
        val score = cards.sumOf { it.getScore() }
        if (cards.any { it.hasAce() && score + 10 <= 21 }) {
            return score + 10
        }
        return score
    }

    fun getProfitMoney(rate: Double): Double = rate * money
}
