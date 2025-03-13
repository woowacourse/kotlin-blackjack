package blackjack.model.participant

@JvmInline
value class Money(
    val value: Long = DEFAULT_MONEY,
) {
    override fun toString(): String = value.toString()

    fun plus(money: Money): Money = Money(this.value + money.value)

    fun minus(money: Money): Money = Money(this.value - money.value)

    companion object {
        const val DEFAULT_MONEY = 0L
    }
}
