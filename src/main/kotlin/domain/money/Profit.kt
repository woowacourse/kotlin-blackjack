package domain.money

data class Profit(val value: Double) {

    constructor(money: Money, rate: Double = 1.0) : this(money.value * rate)
}
