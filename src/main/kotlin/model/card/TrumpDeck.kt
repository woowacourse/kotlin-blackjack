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
        val suits = Suit.entries
        val denominations = Denomination.entries

        cards.addAll(
            suits.flatMap { suit ->
                denominations.map { denom,
                    ->
                    Card.of(denom, suit)
                }.shuffled()
            },
        )
    }
}
