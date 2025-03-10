package blackjack.domain

enum class Face(
    override val possibleValues: Set<Int>,
) : Rank {
    JACK(setOf(10)),
    QUEEN(setOf(10)),
    KING(setOf(10)),
}
