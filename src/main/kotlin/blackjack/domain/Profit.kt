package blackjack.domain

@JvmInline
value class Profit private constructor(
    val value: Double,
) {
    operator fun plus(other: Profit): Profit = Profit(this.value + other.value)

    companion object {
        fun from(
            bettingAmount: BettingAmount,
            gameResult: GameResult,
        ): Profit = Profit(bettingAmount * gameResult.rate)

        fun Iterable<Profit>.sum(): Profit = this.fold(Profit(0.0)) { acc, profit -> acc + profit }
    }
}
