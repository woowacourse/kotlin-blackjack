package blackjack.domain

class Cards() {

    private val _cards: MutableList<Card> = mutableListOf()
    val values: List<Card>
        get() = _cards.toList()

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun sumCardsNumber(): Int {
        var result = 0
        _cards.forEach {
            result += it.number.value
        }
        return result
    }
}
