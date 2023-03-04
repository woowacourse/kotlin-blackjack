package blackjack.domain

class Card(
    val number: CardNumber,
    private val shape: Shape,
) {

    // TODO: UI로직 분리하기
    override fun toString(): String {
        var numberValue = number.name
        if (numberValue.length != SPECIAL_CARDS_NAME_LENGTH) {
            numberValue = number.value.toString()
        }
        return numberValue + shape.description
    }

    companion object {
        private const val SPECIAL_CARDS_NAME_LENGTH = 1

        // TODO: filter를 사용하는 것이 좋은 방법이 아닌것 같다.
        private val ALL_CARDS: MutableSet<Card> = CardNumber
            .values()
            .filter { cardNumber -> CardNumber.BIG_A != cardNumber }
            .flatMap { cardNumber ->
                Shape.values().map { shape ->
                    Card(cardNumber, shape)
                }
            }.toMutableSet()

        fun draw(): Card {
            val card = ALL_CARDS.random()
            ALL_CARDS.remove(card)

            return card
        }
    }
}
