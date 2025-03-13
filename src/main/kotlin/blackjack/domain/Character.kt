package blackjack.domain

enum class Character(
    override val possibleValues: List<Int>,
) : Rank {
    JACK(listOf(10)),
    QUEEN(listOf(10)),
    KING(listOf(10)),
}
