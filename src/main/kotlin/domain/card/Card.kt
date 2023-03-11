package domain.card

data class Card(private val shape: CardShape, val value: CardValue) {

    val number: Int = value.number

    override fun toString(): String {
        return "${value.cardSign}${shape.pattern}"
    }
}
