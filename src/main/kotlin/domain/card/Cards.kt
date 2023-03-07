package domain.card

class Cards(cards: List<Card>) {
    private val _value = cards.toMutableList()
    val value: List<Card>
        get() = _value.toList()

    fun add(card: Card) {
        _value.add(card)
    }

    fun countAce() = _value.count { it.number == CardNumber.ACE }
}
