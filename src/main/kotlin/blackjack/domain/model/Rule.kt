package blackjack.domain.model

class Rule {
    companion object {
        fun calculateResultByCards(cards: Collection<Card>): Int {
            val cardValues = cards.map { it.cardNumber.cardNumber }
            val minimumSum = cardValues.sum()

            if (1 in cardValues && (minimumSum + 10) <= BLACK_JACK_NUMBER) {
                return minimumSum + 10
            }
            return minimumSum
        }

        private const val BLACK_JACK_NUMBER = 21
    }
}
