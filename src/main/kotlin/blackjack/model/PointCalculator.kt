package blackjack.model

import blackjack.controller.BlackJackController.Companion.BLACKJACK_NUMBER

fun interface PointCalculator {
    fun sumOf(cards: List<Card>): Int
}

class DefaultPointCalculator : PointCalculator {
    override fun sumOf(cards: List<Card>): Int {
        if (cards.hasAce().not()) return sumIfExcludeAce(cards)
        return sumIfIncludeAce(cards)
    }

    private fun sumIfExcludeAce(cards: List<Card>): Int {
        val sum = cards.sum()
        if (sum > BLACKJACK_NUMBER) return sum
        return sum
    }

    private fun sumIfIncludeAce(cards: List<Card>): Int {
        val condition = BLACKJACK_NUMBER
        val min = cards.sum()
        val max = min + Rank.ACE.bonusNumber
        if (min > condition) return min
        if (max <= condition) return max
        return min
    }

    private fun List<Card>.sum() = sumOf { it.rank.point }

    private fun List<Card>.hasAce(): Boolean {
        return any { it.isAce() }
    }
}