package blackjack.domain.participants

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber

class Hand(cards: List<Card> = emptyList()) {
    private val _cards: MutableList<Card> = cards.map { it.copy() }.toMutableList()

    val cards: List<Card>
        get() = _cards.toList()

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun isBlackjack(): Boolean {
        return calculateScore() == BLACKJACK_SCORE && _cards.size == FIRST_TURN_DRAW_AMOUNT
    }

    fun isBust(): Boolean = calculateScore() > BLACKJACK_SCORE

    fun calculateScore(): Int {
        val values = _cards.map { getCardValue(it) }
        val sum = values.sum()
        return adjustAceValues(sum, values.count { it == ACE_HIGH_VALUE })
    }

    private fun getCardValue(card: Card): Int {
        return if (card.number == CardNumber.ACE) ACE_HIGH_VALUE else card.number.value
    }

    private fun adjustAceValues(
        sum: Int,
        aceCount: Int,
    ): Int {
        var total = sum
        var remainingAces = aceCount

        while (total > BLACKJACK_SCORE && remainingAces > 0) {
            total -= ACE_VALUE_DIFFERENCE
            remainingAces--
        }

        return total
    }

    companion object {
        private const val BLACKJACK_SCORE = 21
        private const val FIRST_TURN_DRAW_AMOUNT = 2
        private const val ACE_HIGH_VALUE = 11
        private const val ACE_VALUE_DIFFERENCE = 10
    }
}
