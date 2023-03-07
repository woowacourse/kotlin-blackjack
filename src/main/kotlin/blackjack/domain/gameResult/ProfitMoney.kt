package blackjack.domain.gameResult

import blackjack.domain.BattingMoney

// TODO: 사실 이 ProfitMoney를 wrapping한 이유는 연산과정을 숨기기 위해서인데 이런 용도로 원시 값을 wrapping해도 될까?
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
