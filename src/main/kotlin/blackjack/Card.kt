package blackjack

import java.lang.IllegalArgumentException


class Card private constructor(val rank: Rank, val suit: Suit) {
    companion object {
        private val POOL: List<Card> = Rank.entries.flatMap { rank ->
            Suit.entries.map { suit ->
                Card(rank, suit)
            }
        }

        fun of(rank: Rank, suit: Suit): Card {
            return POOL.find { it.rank == rank && it.suit == suit } ?: throw IllegalArgumentException()
        }
    }
}
