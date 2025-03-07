package blackjack.domain

class Number(
    val value: Int,
) : Rank {
    override val possibleValues: List<Int> = listOf(value)

    init {
        require(possibleValues.all { possibleValue: Int -> possibleValue in RANGE })
    }

    companion object {
        private const val MIN = 2
        private const val MAX = 10
        val RANGE = MIN..MAX
    }
}
