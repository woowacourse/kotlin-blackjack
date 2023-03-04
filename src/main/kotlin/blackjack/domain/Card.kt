package blackjack.domain

class Card(
    val number: CardNumber,
    private val shape: Shape
) {

    override fun toString(): String {
        var numberValue = number.name
        if (numberValue.length != SPECIAL_CARDS_NAME_LENGTH) {
            numberValue = number.value.toString()
        }
        return numberValue + shape.description
    }

    companion object {
        private const val SPECIAL_CARDS_NAME_LENGTH = 1

        //TODO: ENUM 값을 모두 가져올 수 있는 API, MAP 연산자를 활용하자
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
