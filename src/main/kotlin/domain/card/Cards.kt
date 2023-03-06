package domain.card

class Cards {
    private val _value = mutableListOf<Card>()
    val value: List<Card>
        get() = _value.toList()

    fun add(card: Card) {
        _value.add(card)
    }

    fun countAce() = _value.count { it.number == CardNumber.ACE }
}
