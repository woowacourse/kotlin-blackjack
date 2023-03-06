package blackjack.domain

class Card private constructor(private val suit: Suit, val number: CardNumber) {
    override fun toString(): String = "${number.toLetter()}${suit.name}"

    companion object {
        private val CARDS = Suit.values().map { shape ->
            (CardNumber.min()..CardNumber.max()).map { Card(shape, CardNumber(it)) }
        }.flatten()

        fun of(id: Int): Card = CARDS[id - 1]
        fun all(): List<Card> = CARDS.toList()
    }
}
