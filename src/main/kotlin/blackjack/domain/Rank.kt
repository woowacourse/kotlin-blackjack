package blackjack.domain

sealed interface Rank {
    val possibleValues: Set<Int>
}

object Ace : Rank {
    override val possibleValues: Set<Int> = setOf(1, 11)
}

enum class Number(
    val value: Int,
) : Rank {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    ;

    override val possibleValues: Set<Int> = setOf(value)
}

enum class Face(
    override val possibleValues: Set<Int>,
) : Rank {
    JACK(setOf(10)),
    QUEEN(setOf(10)),
    KING(setOf(10)),
}
