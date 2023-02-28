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
    JACK(10);
    fun getScore(score: Int): Int {
        return when (this) {
            ACE -> {
                if (score + 1 <= 21) {
                    1
                } else {
                    11
                }
            }
            else -> this.score
        }
    }
}
