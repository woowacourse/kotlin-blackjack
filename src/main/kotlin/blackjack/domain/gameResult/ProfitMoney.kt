package blackjack.domain.gameResult

import blackjack.domain.player.BattingMoney

@JvmInline
value class ProfitMoney(val value: Int) {

    constructor(
        battingMoney: BattingMoney,
        gameResult: GameResult,
    ) : this((battingMoney.value * gameResult.profitRate).toInt())

    operator fun not(): ProfitMoney {
        return ProfitMoney(-value)
    }
}
