package model

@JvmInline
value class Profit(val value: Long) {
    companion object {
        fun of(money: Money, cardGameResult: CardGameResult): Profit = Profit((money.value * cardGameResult.multiple).toLong())
    }
}
