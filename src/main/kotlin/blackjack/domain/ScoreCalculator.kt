package blackjack.domain

import blackjack.const.GameRule
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber

class ScoreCalculator {
    fun calculate(cards: List<Card>): Int {
        val values = cards.map { getCardValue(it) }
        val sum = values.sum()
        return adjustAceValues(sum, values)
    }

    private fun getCardValue(card: Card): Int {
        if (card.number == CardNumber.ACE) return GameRule.ACE_OTHER_SCORE
        return card.number.value
    }

    private fun adjustAceValues(
        sum: Int,
        values: List<Int>,
    ): Int {
        var total = sum
        val aceCount = values.count { it == GameRule.ACE_OTHER_SCORE }

        repeat(aceCount) {
            if (total > GameRule.BLACKJACK_SCORE) {
                total -= GameRule.ACE_BASE_SCORE
            }
        }
        return total
    }
}
