package blackjack.domain.card

class Card private constructor(
    val rank: Rank,
    val suit: Suit,
) {
    val possibleScores: Set<Int> = rank.possibleValues

    companion object {
        private val cardCache = mutableMapOf<Pair<Rank, Suit>, Card>()

        operator fun invoke(
            rank: Rank,
            suit: Suit,
        ): Card = cardCache.getOrPut(rank to suit) { Card(rank, suit) }
    }
}
