package blackjack.domain.card

class CardDeck(cardsGenerator: CardsGenerator) {

    private val _cards: MutableList<Card> = cardsGenerator.generate().toMutableList()
    val cards: List<Card> get() = _cards.toList()

    fun checkProvidePossible(): Boolean {
        if (_cards.isNotEmpty()) return true
        return false
    }

    fun provide(): Card {
        val card = _cards[0]
        _cards.removeAt(0)
        return card
    }
}
