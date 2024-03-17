package blackjack.model.result

@JvmInline
value class Money(val amount: Long) : Comparable<Money> {
    operator fun plus(other: Money): Money = Money(this.amount.plus(other.amount))

    operator fun minus(other: Money): Money = Money(this.amount.minus(other.amount))

    operator fun times(count: Double): Money = Money((this.amount * count).toLong())

    override fun compareTo(other: Money): Int = (amount - other.amount).toInt()

    companion object {
        val ZERO: Money = Money(0)
    }
}
