package blackjack.model

@JvmInline
value class Cards(
    val cards: List<Card>,
) : List<Card> by cards {
    constructor(vararg cards: Card) : this(cards.toList())

    companion object {
        private val DECK: Cards = createDeck()

        @JvmStatic
        fun createDeck(size: Int = 1): Cards {
            require(size > 0)
            if (size == 1) return DECK
            return Cards(
                List(size) { createDeck().cards }.flatten(),
            )
        }

        @JvmStatic
        private fun createDeck(): Cards {
            val suits = Suit.entries
            val ranks = Rank.entries
            val deck: List<Card> =
                suits.flatMap { suit ->
                    ranks.map { rank -> Card(suit, rank) }
                }.shuffled()
            return Cards(deck)
        }
    }
}
