package blackjack.domain.card

data class Card(
    val number: CardNumber,
    val shape: CardShape
) {

    companion object {
        private val CARDS: List<Card> = createCards()

        fun from(card: Card): Card {
            return CARDS.find { card == it } ?: throw IllegalArgumentException()
        }

        private fun createCards(): List<Card> =
            CardNumber.values().flatMap { number -> cardShapesMap(number) }.toList()

        private fun cardShapesMap(number: CardNumber): List<Card> =
            CardShape.values().map { shape -> Card(number, shape) }.toList()
    }
}
