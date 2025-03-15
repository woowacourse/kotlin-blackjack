package blackjack.model.participant

@JvmInline
value class Money(
    val value: Double,
) {
    operator fun plus(other: Money): Money = Money(this.value + other.value)

    operator fun minus(other: Money): Money = Money(this.value - other.value)

    operator fun times(number: Double): Money = Money(this.value * number)

    operator fun unaryMinus(): Money = Money(-this.value)

    operator fun compareTo(other: Money): Int = this.value.compareTo(other.value)

    override fun toString(): String = value.toString()

    companion object {
        val ZERO = Money(0.0)
    }
}
