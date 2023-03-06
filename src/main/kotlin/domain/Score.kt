package domain

class Score(val value: Int) {
    fun isBurst(): Boolean = (value > SCORE_CONDITION)

    fun isOver(score: Score) = (value > score.value)

    companion object {
        private const val SCORE_CONDITION = 21
    }
}
