package blackjack.domain.model.progress

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Number.ACE

class Rule {
    companion object {
        fun calculateResultByCards(cards: Collection<Card>): Int {
            val cardNumbers = cards.map { it.number }
            val minimumSum = calculateMinimumSumByCards(cards)

            if (ACE in cardNumbers && (minimumSum + 10) <= BLACK_JACK_NUMBER) {
                return minimumSum + 10
            }
            return minimumSum
        }

        fun isBurst(cards: Collection<Card>): Boolean {
            val minimumSum = calculateMinimumSumByCards(cards)
            return minimumSum > BLACK_JACK_NUMBER
        }

        fun calculateShouldDrawByCards(cards: Collection<Card>): Boolean {
            val resultValue = calculateResultByCards(cards)
            return resultValue <= DEALER_DRAW_LIMIT
        }

        private fun calculateMinimumSumByCards(cards: Collection<Card>): Int {
            val cardValues = cards.map { it.getMinimumValue() }
            return cardValues.sum()
        }

        private const val BLACK_JACK_NUMBER = 21
        private const val DEALER_DRAW_LIMIT = 16
    }
}
