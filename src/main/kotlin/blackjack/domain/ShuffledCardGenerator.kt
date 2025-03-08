package blackjack.domain

import blackjack.enums.Rank
import blackjack.enums.Suit

class ShuffledCardGenerator {
    fun generate(): List<Card> =
        Suit.entries
            .flatMap { suit -> Rank.entries.map { rank -> Card(rank, suit) } }
            .shuffled()
}
