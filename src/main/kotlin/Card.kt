class Card(
    val number: CardNumber,
    val shape: Shape
) {

    override fun toString(): String {
        var numberValue = number.name
        if (numberValue.length != 1) {
            numberValue = number.value.toString()
        }
        return numberValue + shape.description
    }

    companion object {

        private val ALL_CARDS: MutableSet<Card> = CardNumber.values().flatMap { cardNumber ->
            listOf(
                Card(cardNumber, Shape.CLOVER),
                Card(cardNumber, Shape.HEART),
                Card(cardNumber, Shape.DIAMOND),
                Card(cardNumber, Shape.SPADE)
            )
        }.toMutableSet()

        fun draw(): Card {
            val card = ALL_CARDS.random()
            ALL_CARDS.remove(card)

            return card
        }
    }
}
