package blackjack.domain.update

@JvmInline
value class NewScore(
    val value: Int,
) {
    val isBlackjackScore: Boolean get() = value == 21
    val isBustedScore: Boolean get() = value > 21

    operator fun compareTo(other: NewScore): Int = value.compareTo(other.value)

    operator fun compareTo(other: Int): Int = value.compareTo(other)

    companion object {
        const val SCORE_BLACKJACK = 21
    }
}
