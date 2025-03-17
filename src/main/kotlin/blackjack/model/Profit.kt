package blackjack.model

@JvmInline
value class Profit(
    val value: Double,
) {
    companion object {
        const val INITIAL_PROFIT = 0.0
    }
}
