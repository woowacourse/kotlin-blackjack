package blackjack.domain

class Card(
    val rank: Rank,
    val suit: Suit,
) {
    val hasSingleValue: Boolean = rank is SingleValueRank
}
