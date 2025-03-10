package blackjack.domain

sealed interface Rank {
    val possibleValues: Set<Int>
}

object Ace : Rank {
    override val possibleValues: Set<Int> = setOf(1, 11)
}

enum class Number2(
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

class Number(
    val value: Int,
) : Rank {
    override val possibleValues: Set<Int> = setOf(value)

    init {
        require(possibleValues.all { possibleValue: Int -> possibleValue in RANGE })
    }

    companion object {
        private const val MIN = 2
        private const val MAX = 10
        val RANGE = MIN..MAX
    }
}

enum class Face(
    override val possibleValues: Set<Int>,
) : Rank {
    JACK(setOf(10)),
    QUEEN(setOf(10)),
    KING(setOf(10)),
}
