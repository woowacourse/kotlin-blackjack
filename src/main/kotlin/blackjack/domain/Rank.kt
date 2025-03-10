package blackjack.domain

sealed interface Rank

sealed interface SingleValueRank : Rank {
    val value: Int

    enum class NumberRank(
        override val value: Int,
    ) : SingleValueRank {
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
    }

    enum class FaceRank(
        override val value: Int,
    ) : SingleValueRank {
        JACK(10),
        QUEEN(10),
        KING(10),
    }
}

sealed interface MultiValueRank : Rank {
    val possibleValues: Set<Int>

    object AceRank : MultiValueRank {
        override val possibleValues: Set<Int> = setOf(1, 11)
    }
}
