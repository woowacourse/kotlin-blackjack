package blackjack.model

@JvmInline
value class Deck(
    val cards: List<Card>,
) : List<Card> by cards {
    constructor(vararg cards: Card) : this(cards.toList())

    fun pull(): Card {
        return cards.shuffled().first()
    }

    fun spread(playerSize: Int): List<Card> {
        return cards.take(DEFAULT_CARDS_COUNT * (DEALER_COUNT + playerSize))
    }

    companion object {
        private val DECK: Deck = create()
        private const val DEFAULT_DECK_SIZE = 1
        private const val DEFAULT_CARDS_COUNT = 2
        private const val DEALER_COUNT = 1

        fun create(size: Int = DEFAULT_DECK_SIZE): Deck {
            require(size >= DEFAULT_DECK_SIZE)
            if (size == DEFAULT_DECK_SIZE) return DECK
            return Deck(
                List(size) { create().cards }.flatten(),
            )
        }

        private fun create(): Deck {
            val suits = Suit.entries
            val ranks = Rank.entries
            val deck: List<Card> =
                suits.flatMap { suit ->
                    ranks.map { rank -> Card(suit, rank) }
                }.shuffled()
            return Deck(deck)
        }
    }
}
