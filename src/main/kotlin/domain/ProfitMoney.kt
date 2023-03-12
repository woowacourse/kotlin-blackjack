package domain

@JvmInline
value class ProfitMoney private constructor(val value: Int) {
    companion object {
        fun of(betMoney: BetMoney, rateOfProfit: Double): ProfitMoney {
            return ProfitMoney((betMoney.value * rateOfProfit).toInt())
        }
    }
}
