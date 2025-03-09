package blackjack.domain

import blackjack.enums.Rank
import blackjack.enums.Suit

class Deck(
    cards: List<Card>,
) {
    private val cards: ArrayDeque<Card> = ArrayDeque(cards)

    fun pick(): Card {
        if (cards.isEmpty()) cards.addAll(CARDS.shuffled())
        return cards.removeLast()
    }

    companion object {
        private val CARDS: List<Card> =
            Suit.entries.flatMap { suit -> Rank.entries.map { rank -> Card(rank, suit) } }

        fun create(): Deck = Deck(CARDS.shuffled())
    }
}
