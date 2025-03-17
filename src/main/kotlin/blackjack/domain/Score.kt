package blackjack.domain

@JvmInline
value class Score(
    val value: Int,
) {
    val isBlackjackScore: Boolean get() = value == SCORE_BLACKJACK
    val isBustedScore: Boolean get() = value > SCORE_BLACKJACK

    operator fun compareTo(other: Score): Int = value.compareTo(other.value)

    operator fun compareTo(other: Int): Int = value.compareTo(other)

    companion object {
        const val SCORE_BLACKJACK = 21
        const val SCORE_DEALER_HIT_UNTIL = 17
    }
}
