package domain.card

data class Card(val shape: CardShape, val number: CardNumber) {
    override fun toString(): String {
        return number.label + shape.label
    }
}
