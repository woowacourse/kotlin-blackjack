package blackjack.model

import blackjack.model.ParticipantsHand.Companion.DEALER_COUNT
import blackjack.model.ParticipantsHand.Companion.DEFAULT_CARDS_COUNT

@JvmInline
value class Deck(val cards: MutableList<Card>) {
    fun pull(): Card {
        return cards.removeFirst()
    }

    fun spread(playerSize: Int): MutableList<Card> {
        val spreadCount = DEFAULT_CARDS_COUNT * (DEALER_COUNT + playerSize)
        val spreadCards = cards.take(spreadCount).toMutableList()
        repeat(spreadCount) {
            cards.removeFirst()
        }
        return spreadCards
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
