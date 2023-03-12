package model

@JvmInline
value class Profit(val value: Long) {
    operator fun unaryMinus() = Profit(-value)
    companion object {
        fun of(money: Money, cardGameResult: CardGameResult): Profit = Profit((money.value * cardGameResult.multiple).toLong())
    }
}
