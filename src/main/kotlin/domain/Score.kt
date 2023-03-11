package domain

@JvmInline
value class Score private constructor(val value: Int) {
    fun isBust(): Boolean = (value > BLACKJACK_CONDITION)

    fun isOver(score: Score) = (value > score.value)

    fun isOver(score: Int) = value > score

    fun isBlackJackRegardlessAce(): Boolean = (value == BLACKJACK_CONDITION)

    fun isBlackJack(numbers: List<Int>, hasAce: Boolean): Boolean =
        (valueOfCards(numbers, hasAce).value == BLACKJACK_CONDITION)

    operator fun plus(other: Score): Score = Score(this.value + other.value)

    companion object {
        private const val BLACKJACK_CONDITION = 21
        private const val SUM_CONDITION = 11
        private const val ACE_EXTRA_SCORE = 10

        fun valueOf(value: Int): Score {
            return Score(value)
        }

        fun valueOfCards(numbers: List<Int>, hasAce: Boolean = false): Score {
            val sum = numbers.sum()
            if (sum <= SUM_CONDITION && hasAce)
                return valueOf(sum + ACE_EXTRA_SCORE)
            return valueOf(sum)
        }
    }
}
