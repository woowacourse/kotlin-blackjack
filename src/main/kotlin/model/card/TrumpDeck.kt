package model.card

object TrumpDeck : Deck {
    private const val MIN_VALUE = 0
    private const val MAX_VALUE = 51

    private val cards: MutableList<Card> =
        (MIN_VALUE..MAX_VALUE)
            .shuffled()
            .map {
                Card.from(it)
            }
            .toMutableList()

    override fun pop(): Card {
        return cards.removeAt(0)
    }
}
