package blackjack.model

@JvmInline
value class Money(val amount: Int) {
    operator fun plus(other: Money): Money = Money(this.amount + other.amount)

    operator fun minus(other: Money): Money = Money(this.amount - other.amount)

    operator fun times(times: Double): Money = Money((this.amount * times).toInt())

    operator fun unaryMinus(): Money = Money(-amount)

    companion object {
        private const val MINIMUM_MONEY_AMOUNT = 1
        private const val EXCEPTION_MONEY_AMOUNT = "베팅 가능한 최소 금액은 ${MINIMUM_MONEY_AMOUNT}입니다."

        fun of(bettingAmount: Int): Money {
            require(bettingAmount >= MINIMUM_MONEY_AMOUNT) { EXCEPTION_MONEY_AMOUNT }
            return Money(bettingAmount)
        }
    }
}
