package blackjack.domain

import blackjack.domain.enums.Rank
import blackjack.domain.enums.Suit

class Deck private constructor() {
    companion object {
        val cards: ArrayDeque<Card> = ArrayDeque(createShuffledDeck())

        fun pick(): Card = cards.removeLast()

        private fun createShuffledDeck(): List<Card> =
            Suit.entries
                .flatMap { suit -> Rank.entries.map { rank -> Card(rank, suit) } }
                .shuffled()
    }
}
