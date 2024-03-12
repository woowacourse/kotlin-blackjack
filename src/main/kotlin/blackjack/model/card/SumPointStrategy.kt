package blackjack.model.card

fun interface SumPointStrategy {
    fun sumOf(cards: List<Card>): Int
}

class OptimalSumPointStrategy(private val lowerBound: Int) : SumPointStrategy {
    override fun sumOf(cards: List<Card>): Int {
        val sum = cards.sum()
        if (cards.hasAce().not()) return sum
        val max = sum + Rank.ACE.bonusNumber
        if (max > lowerBound) return sum
        return max
    }

    private fun List<Card>.sum() = sumOf { it.rank.point }

    private fun List<Card>.hasAce(): Boolean {
        return any { it.isAce() }
    }
}
