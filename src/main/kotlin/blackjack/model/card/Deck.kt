package blackjack.model.card

import blackjack.model.Rank
import blackjack.model.Suit

data class Deck(
    val cards: List<Card>,
) : List<Card> by cards {
    fun pull(): Card {
        return cards.shuffled().first()
    }

    fun spread(playerSize: Int): List<Card> {
        return cards.take(INIT_HANDS_COUNT * (DEALER_COUNT + playerSize))
    }

    companion object {
        private const val INIT_HANDS_COUNT = 2
        private val DECK: Deck = create()
        private const val DEFAULT_DECK_SIZE = 1
        private const val DEALER_COUNT = 1

        @JvmStatic
        fun create(size: Int = DEFAULT_DECK_SIZE): Deck {
            require(size >= DEFAULT_DECK_SIZE)
            if (size == DEFAULT_DECK_SIZE) return DECK
            return Deck(
                List(size) { create().cards }.flatten(),
            )
        }

        @JvmStatic
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
