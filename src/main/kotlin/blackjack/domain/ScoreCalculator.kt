package blackjack.domain

import blackjack.const.GameRule
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber

object ScoreCalculator {
    fun calculate(cards: List<Card>): Int {
        val values = cards.map { getCardValue(it) }
        val sum = values.sum()
        return adjustAceValues(sum, values)
    }

    private fun getCardValue(card: Card): Int {
        if (card.number == CardNumber.ACE) return ACE_OTHER_SCORE
        return card.number.value
    }

    private fun adjustAceValues(
        sum: Int,
        values: List<Int>,
    ): Int {
        var total = sum
        val aceCount = values.count { it == ACE_OTHER_SCORE }

        repeat(aceCount) {
            if (total > GameRule.BLACKJACK_SCORE) {
                total -= ACE_BASE_SCORE
            }
        }
        return total
    }

    private const val ACE_BASE_SCORE = 10
    private const val ACE_OTHER_SCORE = 11
}
