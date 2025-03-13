package blackjack.model

class Amount(private var value: Double) {
    fun toMinus(): Amount = Amount(-value)

    fun getValue(): Double = value

    fun addMoney(amount: Amount) {
        value = value + amount.value
    }

    fun toBlackjackMoney():Amount = Amount(value * 1.5)


}
