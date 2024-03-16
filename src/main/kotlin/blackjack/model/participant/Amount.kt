package blackjack.model.participant

class Amount(val price: Int) {
    constructor(amount: Double) : this(amount.toInt())

    operator fun times(other: Double): Amount {
        return Amount(price * other)
    }
}
