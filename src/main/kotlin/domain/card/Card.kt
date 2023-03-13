package domain.card

class Card private constructor(val shape: Shape, val cardValue: CardValue) {
    companion object {
        private val CARDS = Shape.values().map { shape ->
            CardValue.values().map { Card(shape, it) }
        }.flatten()

        fun getAllCard(): List<Card> {
            return CARDS
        }

        fun from(shape: Shape, cardValue: CardValue): Card {
            return CARDS.find { it.shape == shape && it.cardValue == cardValue }!!
        }
    }
}
