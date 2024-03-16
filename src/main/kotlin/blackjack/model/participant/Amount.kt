package blackjack.model.participant

class Amount(val price: Int) {
    constructor(amount: Double) : this(amount.toInt())

    operator fun times(other: Double) = Amount(price * other)

    operator fun plus(other: Amount) = Amount(price + other.price)
}
