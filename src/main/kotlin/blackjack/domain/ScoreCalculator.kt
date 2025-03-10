package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.person.Person

object ScoreCalculator {
    const val BLACKJACK_SCORE = 21
    private const val ACE_BASE_SCORE = 10
    private const val ACE_OTHER_SCORE = 11

    fun calculate(cards: List<Card>): Int {
        val values = cards.map { getCardValue(it) }
        val sum = values.sum()
        return adjustAceValues(sum, cards)
    }

    private fun getCardValue(card: Card): Int {
        if (card.number == CardNumber.ACE) return ACE_OTHER_SCORE
        return card.number.value
    }

    private fun adjustAceValues(
        sum: Int,
        cards: List<Card>,
    ): Int {
        var total = sum
        val aceCount = cards.count { it.number == CardNumber.ACE }

        repeat(aceCount) {
            if (total > BLACKJACK_SCORE) {
                total -= ACE_BASE_SCORE
            }
        }
        return total
    }
}

fun Person.calculateScore(): Int = ScoreCalculator.calculate(this.cards())
