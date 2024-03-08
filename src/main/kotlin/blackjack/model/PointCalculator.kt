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
        return cards.sum()
    }

    private fun sumIfIncludeAce(cards: List<Card>): Int {
        val sumOfCards = cards.sum()
        if (sumOfCards <= BLACKJACK_NUMBER - Rank.ACE.bonusNumber) {
            return sumOfCards + Rank.ACE.bonusNumber
        }
        return sumOfCards
    }

    private fun List<Card>.sum() = sumOf { it.rank.point }

    private fun List<Card>.hasAce(): Boolean {
        return any { it.isAce() }
    }
}
