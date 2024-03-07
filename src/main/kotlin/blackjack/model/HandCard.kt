package blackjack.model

class HandCard {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card>
        get() = _cards.toList()

    fun add(card: Card) {
        _cards.add(card)
    }

    fun getAceCount(): Int {
        return _cards.count { it.denomination.isAce() }
    }
}
