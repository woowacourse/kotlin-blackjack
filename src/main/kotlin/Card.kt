data class Card(
    val symbol: Shape,
    val number: CardNumber,
) {
    companion object {
        private val symbols = Shape.entries
        private val cardNumbers = CardNumber.entries

        val deck = symbols.flatMap { symbol -> cardNumbers.map { cardNumber -> Card(symbol, cardNumber) } }
    }
}
