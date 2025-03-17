package blackjack.domain.model

@JvmInline
value class Score(val value: Int) {
    fun isBlackJack(count: Int) = value == MAX_SCORE && count == BLACK_JACK_CARD_COUNT

    fun isBustScore() = value > MAX_SCORE

    fun isMaxScore() = value == MAX_SCORE

    fun isDealerStay() = value > DEALER_MIN_STAY_SCORE

    operator fun plus(other: Int) = Score(value + other)

    operator fun compareTo(other: Score): Int {
        return this.value - other.value
    }

    override fun toString(): String = value.toString()

    private companion object {
        const val MAX_SCORE = 21
        const val BLACK_JACK_CARD_COUNT = 2
        const val DEALER_MIN_STAY_SCORE = 17
    }
}
