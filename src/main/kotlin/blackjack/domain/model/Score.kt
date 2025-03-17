package blackjack.domain.model

@JvmInline
value class Score(val value: Int) {
    fun isBlackJack(count: Int) = value == 21 && count == 2

    fun isBustScore() = value > 21

    fun isMaxScore() = value == 21

    fun isDealerStay() = value > 17

    operator fun plus(other: Int) = Score(value + other)

    operator fun compareTo(other: Score): Int {
        return this.value - other.value
    }

    override fun toString(): String = value.toString()
}
