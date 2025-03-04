data class Card(
    val symbol: String,
    val number: CardNumber,
) {
    companion object {
        private const val DIAMOND = "다이아몬드"
        private const val HEART = "하트"
        private const val CLOVER = "클로버"
        private const val SPADE = "스페이드"

        private val symbols = listOf(DIAMOND, HEART, CLOVER, SPADE)
        private val cardNumbers = CardNumber.entries

        val deck = symbols.flatMap { symbol -> cardNumbers.map { cardNumber -> Card(symbol, cardNumber) } }
    }
}
