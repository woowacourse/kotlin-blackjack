package blackjack.model

class Money(
    var value: Double,
) {
    operator fun plus(money: Money) {
        value = value + money.value
    }

    fun multiplyMoney(factor: Double): Money = Money(value * factor)
}
