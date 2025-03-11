package blackjack.domain

@JvmInline
value class Score(
    val score: Int,
) {
    operator fun compareTo(other: Score): Int = this.score - other.score

    fun isBust(): Boolean = score > BUST_SCORE

    companion object {
        private const val BUST_SCORE = 21
    }
}
