package blackjack.model

@JvmInline
value class ProfitRate(val amount: Double) {
    constructor(amount: Int) : this(amount.toDouble())
}
