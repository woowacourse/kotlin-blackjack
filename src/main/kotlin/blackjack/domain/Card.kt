package blackjack.domain

class Card(
    val rank: Rank,
    val suit: Suit,
) {
    fun getNumber(): Int = rank.number

    fun isAce(): Boolean = rank == Rank.ACE
}
