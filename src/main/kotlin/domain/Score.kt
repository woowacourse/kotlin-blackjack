package domain

@JvmInline
value class Score private constructor(val value: Int) {
    fun isBurst(): Boolean = (value > BLACKJACK_CONDITION)

    fun isOver(score: Score) = (value > score.value)

    fun isUnder(score: Score) = (value <= score.value)

    fun isBlackJack(): Boolean = (value == BLACKJACK_CONDITION)

    operator fun plus(other: Score): Score = Score(this.value + other.value)

    companion object {
        private const val BLACKJACK_CONDITION = 21
        fun valueOf(value: Int): Score {
            return Score(value)
        }
    }
}
