package blackjack.domain

@JvmInline
value class Score(
    val score: Int,
) {
    operator fun compareTo(other: Score): Int = this.score - other.score

    fun isBust(): Boolean = score > BLACKJACK_SCORE

    fun isPerfectScore(): Boolean = score == BLACKJACK_SCORE

    companion object {
        private const val BLACKJACK_SCORE = 21
    }
}
