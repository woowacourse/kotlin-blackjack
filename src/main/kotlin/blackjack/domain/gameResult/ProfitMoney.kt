package blackjack.domain.gameResult

import blackjack.domain.BattingMoney

class ProfitMoney(val value: Int) {

    operator fun not(): ProfitMoney {
        return ProfitMoney(-value)
    }

    constructor(
        battingMoney: BattingMoney,
        gameResult: GameResult,
    ) : this(battingMoney.value * gameResult.profitRate.toInt())
}
