package blackjack.model

fun interface PointCalculator {
    fun sumOrNull(cards: List<Card>): Int?
}

class DefaultPointCalculator : PointCalculator {
    override fun sumOrNull(cards: List<Card>): Int? {
        if (cards.hasAce().not()) return sumIfExcludeAce(cards)
        return sumIfIncludeAce(cards)
    }

    private fun List<Card>.hasAce(): Boolean {
        return any { it.isAce() }
    }

    private fun sumIfExcludeAce(cards: List<Card>): Int? {
        val sum = cards.sum()
        if (sum > 21) return null
        return sum
    }

    private fun sumIfIncludeAce(cards: List<Card>): Int? {
        val (aceCards, notAceCards) = cards.partition { it.isAce() }
        val condition = 21
        val min = minSumOf(aceCards, notAceCards)
        val max = maxSumOf(aceCards, notAceCards)
        if (min > condition) return null
        if (max <= condition) return max
        return min
    }

    private fun minSumOf(
        aceCards: List<Card>,
        notAceCards: List<Card>,
    ) = aceCards.size + notAceCards.sum()

    private fun maxSumOf(
        aceCards: List<Card>,
        notAceCards: List<Card>,
    ) = minSumOf(aceCards, notAceCards) + 10

    private fun List<Card>.sum() = sumOf { it.rank.point }
}
