package blackjack.domain.card

class Cards(cards: List<Card> = listOf()) {

    constructor(vararg cards: Pair<CardNumber, CardShape>) : this(cards.map { Card.from(it.first, it.second) }.toList())

    private val _cards = cards.toMutableList()

    val values: List<Card>
        get() = _cards.toList()
    val size: Int
        get() = _cards.size

    fun add(card: Card) {
        _cards.add(card)
    }

    fun containsCardNumber(cardNumber: CardNumber): Boolean =
        _cards.any { it.number == cardNumber }

    fun sum(): Int = _cards.sumOf { it.number.value } + bonus()

    private fun bonus(): Int = if (_cards.size == 2 && containsCardNumber(CardNumber.ONE)) 10 else 0
}
