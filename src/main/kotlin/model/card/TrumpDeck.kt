package model.card

object TrumpDeck : Deck {
    private val cards: MutableList<Card> =
        (0..51)
            .shuffled()
            .map {
                Card.from(it)
            }
            .toMutableList()

    override fun pop(): Card {
        return cards.removeAt(0)
    }
}
