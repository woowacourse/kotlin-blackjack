package domain.card

class Card private constructor(
    val shape: Shape,
    val cardNumber: CardNumber,
) {

    companion object {
        private val cardCache: MutableMap<Pair<Shape, CardNumber>, Card> = mutableMapOf()
        fun of(
            shape: Shape,
            cardNumber: CardNumber,
        ): Card {
            return cardCache.getOrPut(shape to cardNumber) { Card(shape, cardNumber) }
        }
    }
}
