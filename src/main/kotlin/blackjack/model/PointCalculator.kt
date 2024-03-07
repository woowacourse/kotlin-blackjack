package blackjack.model

fun interface PointCalculator {
    fun sumOrNull(cards: List<Card>): Int?
}

class DefaultPointCalculator : PointCalculator {
    override fun sumOrNull(cards: List<Card>): Int? {
        if (cards.hasAce().not()) return sumIfExcludeAce(cards)
        return sumIfIncludeAce(cards)
    }

    private fun sumIfExcludeAce(cards: List<Card>): Int? {
        val sum = cards.sum()
        if (sum > 21) return null
        return sum
    }

    private fun sumIfIncludeAce(cards: List<Card>): Int? {
        val condition = 21
        val min = cards.sum()
        val max = min + Rank.ACE.bonusNumber
        if (min > condition) return null
        if (max <= condition) return max
        return min
    }

    private fun List<Card>.sum() = sumOf { it.rank.point }

    private fun List<Card>.hasAce(): Boolean {
        return any { it.isAce() }
    }
}
