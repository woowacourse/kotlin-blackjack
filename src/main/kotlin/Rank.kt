enum class Rank(private val score: Int) {
    ACE(1),
    DEUCE(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    KING(10),
    QUEEN(10),
    JACK(10),
    ;

    fun getScore(score: Int): Int {
        return when (this) {
            ACE -> {
                if (score + ACE_SCORE_ELEVEN > WIN_SCORE) {
                    ACE_SCORE_ONE
                } else {
                    ACE_SCORE_ELEVEN
                }
            }

            else -> this.score
        }
    }

    companion object {
        private const val ACE_SCORE_ONE = 1
        private const val ACE_SCORE_ELEVEN = 11
        private const val WIN_SCORE = 21
    }
}
