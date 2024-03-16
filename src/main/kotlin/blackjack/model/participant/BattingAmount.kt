package blackjack.model.participant

class BattingAmount(val amount: Int) {
    constructor(amount: Double) : this(amount.toInt())

    operator fun times(other: Double): BattingAmount {
        return BattingAmount(amount * other)
    }
}
