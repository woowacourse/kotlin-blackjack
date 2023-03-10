package blackjack.domain.card

class CardDeck(cardsGenerator: CardsGenerator) {

    private val _cards: MutableList<Card> = cardsGenerator.generate().toMutableList()
    val cards: List<Card> get() = _cards.toList()

    fun provide(): Card? {
        val card = _cards.getOrNull(0) ?: return null
        _cards.removeAt(0)
        return card
    }
}
