package domain.card

data class Card(private val shape: CardShape, val value: CardValue) {

    override fun toString(): String {
        return "${value.cardSign}${shape.pattern}"
    }
}
