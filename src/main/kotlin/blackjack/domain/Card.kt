package blackjack.domain

class Card(
    val rank: Rank,
    val suit: Suit,
) {
    val possibleScore: Set<Int> = rank.possibleValues
}
