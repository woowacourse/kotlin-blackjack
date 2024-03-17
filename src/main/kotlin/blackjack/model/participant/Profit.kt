package blackjack.model.participant

import blackjack.model.result.GameResultType

class Profit(var profit: Double = 0.0) {
    fun calculateProfit(
        battingAmount: BattingAmount,
        result: GameResultType,
    ): Profit {
        return Profit(result.rate * battingAmount.amount)
    }
}
