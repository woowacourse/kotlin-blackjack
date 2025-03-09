package blackjack.domain

import blackjack.const.GameRule
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber

object ScoreCalculator {
    fun calculate(cards: List<Card>): Int {
        val values = cards.map { getCardValue(it) }
        val sum = values.sum()
        return adjustAceValues(sum, cards)
    }

    private fun getCardValue(card: Card): Int {
        if (card.number == CardNumber.ACE) return GameRule.ACE_OTHER_SCORE
        return card.number.value
    }

    private fun adjustAceValues(
        sum: Int,
        cards: List<Card>,
    ): Int {
        var total = sum
        val aceCount = cards.count { it.number == CardNumber.ACE }

        repeat(aceCount) {
            if (total > GameRule.BLACKJACK_SCORE) {
                total -= GameRule.ACE_BASE_SCORE
            }
        }
        return total
    }
}
