package model.card

object TrumpDeck : Deck {
    private val cards = mutableListOf<Card>()
    private const val FIRST_CARD_POSITION = 0

    override fun pop(): Card {
        if (cards.isEmpty()) shuffle()
        return cards.removeAt(FIRST_CARD_POSITION)
    }

    private fun shuffle() {
        val suits = Suit.entries
        val denominations = Denomination.entries

        cards.addAll(
            suits.flatMap { suit ->
                denominations.map { denom,
                    ->
                    Card.of(denom, suit)
                }
            }.shuffled(),
        )
    }
}
