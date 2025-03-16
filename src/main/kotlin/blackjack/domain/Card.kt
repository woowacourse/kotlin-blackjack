package blackjack.domain

class Card(
    val rank: Rank,
    val suit: Suit,
) {
    fun getScore(): Int = rank.score

    fun hasAce(): Boolean = rank == Rank.ACE
}
