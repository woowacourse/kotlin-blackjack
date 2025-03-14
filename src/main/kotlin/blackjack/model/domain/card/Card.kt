package blackjack.model.domain.card

data class Card(
    val symbol: Shape,
    val cardNumber: CardNumber,
) {
    companion object {
        private val symbols = Shape.entries
        private val cardNumbers = CardNumber.entries
        private const val NONE_CARD = "해당 카드는 없습니다."

        private val cards =
            symbols.flatMap { symbol -> cardNumbers.map { cardNumber -> Card(symbol, cardNumber) } }
        val CARDDECK: Map<String, Card> = cards.associateBy { "${it.cardNumber}${it.symbol}" }

        fun from(key: String): Card {
            return CARDDECK[key] ?: throw IllegalArgumentException(NONE_CARD)
        }
    }
}
