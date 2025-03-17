package blackjack.domain.card

sealed interface Rank {
    val possibleValues: Set<Int>

    object AceRank : Rank {
        override val possibleValues: Set<Int> = setOf(1, 11)
    }

    enum class NumberRank(
        vararg values: Int,
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

        override val possibleValues: Set<Int> = setOf(*(values.toTypedArray()))
    }

    enum class FaceRank(
        vararg values: Int,
    ) : Rank {
        JACK(10),
        QUEEN(10),
        KING(10),
        ;

        override val possibleValues: Set<Int> = setOf(*(values.toTypedArray()))
    }
}
