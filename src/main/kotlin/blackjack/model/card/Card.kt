package blackjack.model.card

import blackjack.model.Rank
import blackjack.model.Suit

data class Card(val suit: Suit, val rank: Rank) {
    fun isAce() = (rank == Rank.ACE)
}
