package model.card

object TrumpDeck : Deck {
    private const val MIN_VALUE = 0
    private const val MAX_VALUE = 51

    private val cards = mutableListOf<Card>()

    override fun pop(): Card {
        if (cards.isEmpty()) shuffle()
        return cards.removeAt(0)
    }

    private fun shuffle() {
        cards.addAll(
            (MIN_VALUE..MAX_VALUE)
                .shuffled()
                .map {
                    Card.from(it)
                }
                .toMutableList(),
        )
    }
}
