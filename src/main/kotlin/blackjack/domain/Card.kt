package blackjack.domain

data class Card private constructor(val shape: Shape, val cardNumber: CardNumber) {
    companion object {
        private val CARDS: List<Card> = Shape.values().flatMap { shape ->
            CardNumber.all().map { number -> Card(shape, number) }
        }

        fun all(): MutableList<Card> = CARDS.toMutableList()

        fun get(shape: Shape, cardNumber: CardNumber): Card =
            CARDS.find { it == Card(shape, cardNumber) } ?: throw IllegalArgumentException()
    }
}
