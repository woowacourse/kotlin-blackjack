package blackjack.model.participant

@JvmInline
value class Money(
    val value: Double,
) {
    fun plus(money: Money): Money = Money(this.value + money.value)

    fun minus(money: Money): Money = Money(this.value - money.value)

    fun multiply(number: Double): Money = Money(this.value * number)

    companion object {
        val ZERO = Money(0.0)
    }
}
