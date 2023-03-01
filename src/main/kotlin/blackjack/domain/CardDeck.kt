package blackjack.domain

class CardDeck(private val generator: CardGenerator) {
    private val deck = Card.all().toMutableList()

    fun draw(): Card {
        val card = generator.generate()
        if (deck.contains(card)) {
            deck.remove(card)
            return card
        }
        return draw()
    }
}
