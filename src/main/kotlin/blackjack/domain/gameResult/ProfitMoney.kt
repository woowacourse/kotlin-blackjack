package blackjack.domain.gameResult

import blackjack.domain.player.BattingMoney

@JvmInline
value class ProfitMoney(val value: Int) {

    operator fun not(): ProfitMoney {
        return ProfitMoney(-value)
    }

    companion object{

        fun of(
            battingMoney: BattingMoney,
            gameResult: GameResult,
        ): ProfitMoney {
            val profitValue = (battingMoney.value * gameResult.profitRate).toInt()

            return ProfitMoney(profitValue)
        }
    }
}
