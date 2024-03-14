package blackjack.model.card

class Deck(val cards: MutableList<Card> = mutableListOf()) {
    init {
        if (cards.isEmpty()) {
            initializeDeck()
        }
    }

    private fun initializeDeck() {
        cards.clear()
        cards.addAll(
            Suit.entries.flatMap { suit ->
                Denomination.entries.map { denomination ->
                    Card(denomination, suit)
                }
            },
        )
        cards.shuffle()
    }

    fun dealCard(): Card = if (cards.isNotEmpty()) cards.removeAt(0) else throw NoSuchElementException("Deck is empty")
}
