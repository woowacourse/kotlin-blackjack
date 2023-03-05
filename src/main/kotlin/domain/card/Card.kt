package domain.card

class Card(private val shape: CardShape, val value: CardValue) {

    override fun toString(): String {
        return "${value.cardSign}${shape.pattern}"
    }

    companion object {
        fun valueOf(card: Card): Int {
            return card.value.number
        }
    }
}
