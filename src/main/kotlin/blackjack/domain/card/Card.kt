package blackjack.domain.card

data class Card private constructor(
    val number: CardNumber,
    val shape: CardShape
) {

    companion object {
        private val CARDS: List<Card> = createCards()

        fun from(cardNumber: CardNumber, cardShape: CardShape): Card {
            return CARDS.find { cardNumber == it.number && cardShape == it.shape } ?: throw IllegalArgumentException()
        }

        private fun createCards(): List<Card> =
            CardNumber.values().flatMap { number -> cardShapesMap(number) }.toList()

        private fun cardShapesMap(number: CardNumber): List<Card> =
            CardShape.values().map { shape -> Card(number, shape) }.toList()
    }
}
