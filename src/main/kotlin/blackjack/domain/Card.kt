package blackjack.domain

import blackjack.domain.enums.Rank
import blackjack.domain.enums.Suit

data class Card(
    private val rank: Rank,
    private val suit: Suit,
) {
    fun getNumber(): Int = rank.number

    fun isAce(): Boolean = rank == Rank.ACE
}
