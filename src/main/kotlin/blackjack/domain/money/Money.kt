package blackjack.domain.money

class Money(private val amount: Int = DEFAULT_AMOUNT) {
    constructor(amount: Double) : this(amount.toInt())

    fun getAmount(): Int = amount

    operator fun times(operand: Double): Money = Money(amount * operand)

    operator fun unaryMinus(): Money = Money(-amount)

    companion object {
        private const val DEFAULT_AMOUNT = 0
    }
}
