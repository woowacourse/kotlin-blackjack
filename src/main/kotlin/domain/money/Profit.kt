package domain.money

data class Profit(val value: Double) {

    companion object {
        fun of(money: Money, rate: Double = 1.0) = Profit(money.value * rate)
    }
}
