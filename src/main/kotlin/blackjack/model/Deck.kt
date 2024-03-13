package blackjack.model

class Deck(cards: List<Card>) {
    private val _cards: MutableList<Card> = cards.toMutableList()
    val cards get() = _cards

    fun pull(): Card {
        return _cards.removeFirst()
    }

    companion object {
        fun create(size: Int = DEFAULT_DECK_SIZE): Deck {
            require(size >= DEFAULT_DECK_SIZE)
            if (size == DEFAULT_DECK_SIZE) return DECK
            return Deck(
                List(size) { create().cards }.flatten().toMutableList(),
            )
        }

        private fun create(): Deck {
            val suits = Suit.entries
            val ranks = Rank.entries
            val deck: MutableList<Card> =
                suits.flatMap { suit ->
                    ranks.map { rank -> Card(suit, rank) }
                }.shuffled().toMutableList()
            return Deck(deck)
        }

        private val DECK: Deck = create()
        private const val DEFAULT_DECK_SIZE = 1
    }
}
