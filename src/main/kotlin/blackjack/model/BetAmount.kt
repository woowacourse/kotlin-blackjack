package blackjack.model

@JvmInline
value class BetAmount(val amount: Int) {
    operator fun plus(other: BetAmount): BetAmount {
        return BetAmount(this.amount + other.amount)
    }
}
