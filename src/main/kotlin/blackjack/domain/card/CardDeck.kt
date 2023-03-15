package blackjack.domain.card

class CardDeck(cardsGenerator: CardsGenerator) {

    private val cards: MutableList<Card> = cardsGenerator.generate().values.toMutableList()

    fun provide(): Card? {
        cards.getOrNull(0) ?: return null
        return cards.removeAt(0)
    }
}
