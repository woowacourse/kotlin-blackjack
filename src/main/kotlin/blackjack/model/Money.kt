package blackjack.model

open class Money(
    value: Int,
) {
    private val initialValue: Int = value
    var value: Double = initialValue.toDouble()
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
