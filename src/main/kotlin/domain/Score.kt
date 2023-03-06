package domain

@JvmInline
value class Score private constructor(val value: Int) {
    fun isBurst(): Boolean = (value > BURST_CONDITION)

    fun isOver(score: Score) = (value > score.value)

    companion object {
        private const val BURST_CONDITION = 21
        fun valueOf(value: Int): Score {
            return Score(value)
        }
    }
}
