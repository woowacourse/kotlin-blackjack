package blackjack.domain.card

class Card(
    val number: CardNumber,
    val shape: Shape,
) {

    companion object {

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
