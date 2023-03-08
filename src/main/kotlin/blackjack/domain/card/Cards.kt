package blackjack.domain.card

class Cards(cards: List<Card> = listOf()) {
    private val _cards = cards.toMutableList()

    val values: List<Card>
        get() = _cards.toList()
    val size: Int
        get() = _cards.size

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun containsCardNumber(cardNumber: CardNumber): Boolean =
        _cards.any { it.number == cardNumber }

    fun sum(): Int = _cards.sumOf { it.number.value }
}
