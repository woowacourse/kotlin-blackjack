package blackjack.model

@JvmInline
value class Profit(val amount: Int) {
    constructor(betting: Betting, profitRate: ProfitRate) : this((betting.amount * profitRate.amount).toInt())

    operator fun unaryMinus(): Profit {
        return Profit(-amount)
    }

    operator fun plus(other: Profit) = Profit(amount + other.amount)
}
