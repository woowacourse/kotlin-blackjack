package blackjack.model

open class Money(
    value: Double,
) {
    private val initialValue: Double = value
    var value: Double = initialValue
        private set

    fun plus(money: Money) {
        value += money.value
    }

    fun minus(money: Money) {
        value -= money.value
    }

    fun multiple(times: Double) {
        value *= times
    }

    fun getProfit(): Double = value - initialValue
}
