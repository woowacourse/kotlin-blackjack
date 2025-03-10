package blackjack.domain

class Card(
    val rank: Rank,
    val suit: Suit,
) {
    fun getNumber(): Int = rank.score

    fun isAce(): Boolean = rank == Rank.ACE
}
