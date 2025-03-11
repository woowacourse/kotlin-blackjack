package blackjack.domain

class Card(
    val rank: Rank,
    val suit: Suit,
) {
    val possibleScores: Set<Int> = rank.possibleValues
}
