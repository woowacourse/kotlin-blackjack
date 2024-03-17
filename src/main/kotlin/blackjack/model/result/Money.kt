package blackjack.model.result

@JvmInline
value class Money(val amount: Long) : Comparable<Money> {
    operator fun plus(other: Money): Money = Money(this.amount.plus(other.amount))

    operator fun minus(other: Money): Money = Money(this.amount.minus(other.amount))

    operator fun times(count: Double): Money = Money((this.amount * count).toLong())

    operator fun unaryMinus(): Money = Money(-amount)

    override fun compareTo(other: Money): Int = (amount - other.amount).toInt()

    companion object {
        val ZERO: Money = Money(0)

        fun bet(amount: Long): Money {
            require(amount >= MIN_BETTING_AMOUNT) { "돈은 $MIN_BETTING_AMOUNT 원 이상 배팅할 수 있습니다." }
            return Money(amount)
        }

        private const val MIN_BETTING_AMOUNT = 1_000
    }
}
