package blackjack.domain.money

open class Money(private val amount: Int) {
    fun getAmount(): Int = amount

    operator fun times(operand: Double): Money = Money((amount * operand).toInt())

    operator fun plus(money: Money): Money = Money(amount + money.amount)

    operator fun unaryMinus(): Money = Money(-amount)
}
