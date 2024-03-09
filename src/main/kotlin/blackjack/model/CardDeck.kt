package blackjack.model

class CardDeck {
    private val _cards: MutableSet<Card> = initializeCardDeck()
    val cards: Set<Card>
        get() = _cards.toSet()

    fun pickCard(): Card {
        val card = _cards.elementAt(0)
        _cards.remove(card)
        return card
    }

    private fun initializeCardDeck(): MutableSet<Card> {
        return CardNumber.entries.flatMap { number -> CardSymbol.entries.map { symbol -> Card(number, symbol) } }
            .shuffled().toMutableSet()
    }
}
