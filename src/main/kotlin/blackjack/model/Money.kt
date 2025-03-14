package blackjack.model

class Money(
    private var value: Double,
) {
    fun toMinus(): Money = Money(-value)

    fun getValue(): Double = value

    fun addMoney(money: Money) {
        value = value + money.value
    }

    fun toBlackjackMoney(): Money = Money(value * 1.5)

    fun multiplyMoney(factor: Double): Money = Money(value * factor)
}
