package blackjack.domain

class Card private constructor(private val suit: Suit, val number: CardNumber) {
    companion object {
        private val CARDS = Suit.values().flatMap { suit ->
            CardNumber.values().map { cardNumber ->
                Card(suit, cardNumber)
            }
        }

        fun of(id: Int): Card = CARDS[id - 1]
        fun all(): List<Card> = CARDS.toList()
    }
}
