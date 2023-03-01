package blackjack.domain

class Card private constructor(private val shape: Shape, val number: CardNumber) {
    override fun toString(): String = "${number.toLetter()}${shape.name}"

    companion object {
        private val CARDS = Shape.values().map { shape ->
            (CardNumber.min()..CardNumber.max()).map { Card(shape, CardNumber(it)) }
        }.flatten()

        fun of(id: Int): Card = CARDS[id - 1]
        fun all(): List<Card> = CARDS.toList()
    }
}
