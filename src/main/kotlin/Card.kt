class Card(val number: CardNumber, val shape: Shape) {
    companion object {
        // TODO: 카드 생성을 간단하게
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
