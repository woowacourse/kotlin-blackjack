package blackjack.model

class CardDeck(cards: List<Card>) {
    private val _cards: MutableSet<Card> = cards.toMutableSet()
    val cards: Set<Card>
        get() = _cards.toSet()

    fun pickCard(): Card {
        val card = _cards.elementAt(0)
        _cards.remove(card)
        return card
    }
}
