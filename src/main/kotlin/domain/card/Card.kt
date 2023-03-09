package domain.card

data class Card(val shape: Shape, val cardValue: CardValue) {
    companion object {
        private val CARDS = Shape.values().map { shape ->
            CardValue.values().map { Card(shape, it) }
        }.flatten()

        fun getAllCard(): List<Card> {
            return CARDS
        }
    }
}
