package blackjack.model

open class Money(
    value: Double,
) {
    protected val initialValue: Double = value
    var value: Double = initialValue
        private set

    fun plus(money: Money) {
        value += money.initialValue
    }

    fun minus(money: Money) {
        value -= money.initialValue
    }

    fun multiple(times: Double): Money = Money(times * initialValue)
}
