package blackjack.domain

class Card(
    private val rank: Rank,
    private val suit: Suit,
) {
    fun getScore(): Int = rank.score
}
