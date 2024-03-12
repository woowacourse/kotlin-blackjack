package blackjack.model.card

fun interface PointCalculator {
    fun sumOf(cards: List<Card>): Int
}

class DefaultPointCalculator(private val lowerBound: Int) : PointCalculator {
    override fun sumOf(cards: List<Card>): Int {
        val sum = cards.sum()
        if (cards.hasAce().not()) return cards.sum()
        if (sum > lowerBound) return sum
        return sum + Rank.ACE.bonusNumber
    }

    private fun List<Card>.sum() = sumOf { it.rank.point }

    private fun List<Card>.hasAce(): Boolean {
        return any { it.isAce() }
    }

    companion object {

    }
}
