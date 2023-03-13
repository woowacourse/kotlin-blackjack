package model

@JvmInline
value class Profit(val value: Long) {
    operator fun unaryMinus() = Profit(-value)
    companion object {
        fun of(money: Money, earningRate: EarningRate): Profit = Profit((money.value * earningRate.multiple).toLong())
    }
}
