package blackjack.model

open class Money(
    value: Double,
) {
    protected val initialValue: Double = value
    var value: Double = initialValue
        private set

    fun plus(money: Money): Money = Money(value + money.value)

    fun minus(money: Money): Money = Money(value - money.value)

    fun multiple(times: Double): Money = Money(times * initialValue)
}
