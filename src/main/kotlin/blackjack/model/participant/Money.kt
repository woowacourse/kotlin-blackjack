package blackjack.model.participant

@JvmInline
value class Money(
    val value: Double,
) {
    override fun toString(): String = value.toString()

    fun plus(money: Money): Money = Money(this.value + money.value)

    fun minus(money: Money): Money = Money(this.value - money.value)

    fun multiply(number: Double): Money = Money(this.value * number)

    fun divide(number: Double): Money = Money(this.value / number)

    companion object {
        const val PLAYER_DEFAULT_MONEY = 1_000_000.0
        const val DEALER_DEFAULT_MONEY = Double.MAX_VALUE
        val ZERO = Money(0.0)
    }
}
