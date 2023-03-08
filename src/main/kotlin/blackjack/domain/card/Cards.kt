package blackjack.domain.card

class Cards(private val _cards: MutableList<Card> = mutableListOf()) {

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
