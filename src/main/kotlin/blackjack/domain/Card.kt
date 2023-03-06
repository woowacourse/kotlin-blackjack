package blackjack.domain

class Card(private val suit: Suit, private val number: CardNumber) {
    companion object {
        private val CARDS = Suit.values().flatMap { suit ->
            CardNumber.values().map { cardNumber ->
                Card(suit, cardNumber)
            }
        }

        fun all(): List<Card> = CARDS.toList()
    }
}
