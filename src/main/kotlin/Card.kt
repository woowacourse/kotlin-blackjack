class Card(number: CardNumber, shape: Shape) {
    companion object {
        private val ALL_CARDS: MutableSet<Card> = (
            CardNumber.values().map { cardNumber -> Card(cardNumber, Shape.CLOVER) }.toSet() +
                CardNumber.values().map { cardNumber -> Card(cardNumber, Shape.SPADE) }.toSet() +
                CardNumber.values().map { cardNumber -> Card(cardNumber, Shape.DIAMOND) }.toSet() +
                CardNumber.values().map { cardNumber -> Card(cardNumber, Shape.HEART) }.toSet()
            ).toMutableSet()

        fun draw(): Card {
            val card = ALL_CARDS.random()
            ALL_CARDS.remove(card)

            return card
        }
    }
}
