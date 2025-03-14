package blackjack.model.participant

@JvmInline
value class Money(
    val value: Double,
) {
    operator fun plus(money: Money): Money = Money(this.value + money.value)

    operator fun minus(money: Money): Money = Money(this.value - money.value)

    operator fun times(number: Double): Money = Money(this.value * number)

    operator fun unaryMinus(): Money = Money(-this.value)

    companion object {
        val ZERO = Money(0.0)
    }
}
