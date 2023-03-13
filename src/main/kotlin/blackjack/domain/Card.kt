package blackjack.domain

class Card private constructor(val shape: Shape, val cardNumber: CardNumber) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Card

        if (shape != other.shape) return false
        if (cardNumber != other.cardNumber) return false

        return true
    }

    override fun hashCode(): Int {
        var result = shape.hashCode()
        result = 31 * result + cardNumber.hashCode()
        return result
    }

    companion object {
        private const val ERROR_NO_CARD = "트럼프 카드에 해당 카드는 없습니다."

        private val CARDS: List<Card> = Shape.values().flatMap { shape ->
            CardNumber.all().map { number -> Card(shape, number) }
        }

        fun all(): MutableList<Card> = CARDS.toMutableList()

        fun get(shape: Shape, cardNumber: CardNumber): Card =
            CARDS.find { it == Card(shape, cardNumber) } ?: throw IllegalArgumentException(ERROR_NO_CARD)
    }
}
