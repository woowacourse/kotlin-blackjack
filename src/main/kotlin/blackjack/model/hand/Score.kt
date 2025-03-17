package blackjack.model.hand

@JvmInline
value class Score(
    val value: Int,
) {
    override fun toString(): String = value.toString()

    operator fun plus(other: Score): Score = Score(this.value + other.value)

    operator fun compareTo(other: Score): Int = this.value.compareTo(other.value)
}
