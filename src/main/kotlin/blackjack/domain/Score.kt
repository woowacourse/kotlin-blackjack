package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber

class Score(val value: Int) {
    fun isBlackJackScore(): Boolean = value == BLACKJACK_SCORE

    fun isBustScore(): Boolean = value > BLACKJACK_SCORE

    fun isDealerStayScore(): Boolean = value > DEALER_ADDITIONAL_DRAW_BASE_SCORE

    companion object {
        private const val ACE_BASE_SCORE = 10
        private const val ACE_OTHER_SCORE = 11
        private const val BLACKJACK_SCORE = 21
        private const val DEALER_ADDITIONAL_DRAW_BASE_SCORE = 16

        fun create(cards: List<Card>): Score {
            val value = calculate(cards)
            return Score(value)
        }

        private fun calculate(cards: List<Card>): Int {
            val sum = cards.sumOf { getCardValue(it) }
            return adjustAceValues(sum, cards)
        }

        private fun getCardValue(card: Card): Int = if (card.number == CardNumber.ACE) ACE_OTHER_SCORE else card.number.value

        private fun adjustAceValues(
            sum: Int,
            cards: List<Card>,
        ): Int {
            val aceCount = cards.count { it.number == CardNumber.ACE }
            var total = sum
            repeat(aceCount) { if (total > BLACKJACK_SCORE) total -= ACE_BASE_SCORE }
            return total
        }
    }
}
