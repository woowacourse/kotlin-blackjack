package blackjack.model

open class Money(
    value: Int,
) {
    val initialValue: Int = value
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
