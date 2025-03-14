package blackjack.model

open class Money(
    value: Int,
) {
    protected val initialValue: Int = value
    var value: Int = initialValue
        private set

    fun plus(money: Money) {
        value += money.initialValue
    }

    fun minus(money: Money) {
        value -= money.initialValue
    }
}
