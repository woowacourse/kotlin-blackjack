package domain.card

class Hand(cards: List<Card>) {
    constructor(vararg card: Card) : this(card.toList())

    private val _value = cards.toMutableList()

    val value: List<Card>
        get() = _value.toList()

    val hasAce
        get() = _value.map { it.number }.contains(CardNumber.ACE)
    fun add(card: Card) {
        _value.add(card)
    }

    fun getTotalCards() = _value.sumOf { it.number.value }
}
