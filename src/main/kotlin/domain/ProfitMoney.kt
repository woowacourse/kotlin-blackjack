package domain

import state.RateOfProfit

@JvmInline
value class ProfitMoney(val value: Int) {
    companion object {
        fun of(betMoney: BetMoney, rateOfProfit: RateOfProfit): ProfitMoney {
            return ProfitMoney((betMoney.value * rateOfProfit.value).toInt())
        }
    }
}
