package blackjack.domain.model

class Rule {
    companion object {
        fun calculateResultByCards(cards: Collection<Card>): Int {
            val cardValues = cards.map { it.cardNumber.cardNumber }
            val minimumSum = calculateMinimumSumByCards(cards)

            if (1 in cardValues && (minimumSum + 10) <= BLACK_JACK_NUMBER) {
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
            val cardValues = cards.map { it.cardNumber.cardNumber }
            return cardValues.sum()
        }

        private const val BLACK_JACK_NUMBER = 21
        private const val DEALER_DRAW_LIMIT = 16
    }
}
