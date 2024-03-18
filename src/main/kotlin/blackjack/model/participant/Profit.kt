package blackjack.model.participant

import blackjack.model.result.GameResultType

class Profit(private val profit: Double = 0.0) {
    fun calculateProfit(
        battingAmount: BattingAmount,
        result: GameResultType,
    ): Profit {
        return Profit(result.rate * battingAmount.amount)
    }

    override fun toString() = profit.toInt().toString()

    operator fun minus(other: Profit): Profit {
        return Profit(profit - other.profit)
    }
}
