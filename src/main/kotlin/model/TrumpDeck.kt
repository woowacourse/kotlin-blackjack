package model

object TrumpDeck : Deck {
    private val cards: MutableList<Card> =
        (0..51).map {
            Card.from(it)
        }.toMutableList()

    override fun pop(): Card {
        return cards.removeAt(0)
    }
}
