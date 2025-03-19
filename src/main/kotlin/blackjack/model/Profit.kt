package blackjack.model

@JvmInline
value class Profit(
    val value: Double,
) {
    operator fun plus(other: Profit): Profit = Profit(value + other.value)

    fun reversed(): Profit = Profit(-value)
}
