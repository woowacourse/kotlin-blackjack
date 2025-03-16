package blackjack.domain

@JvmInline
value class Betting(
    val amount: Double,
) {
    init {
        require(amount > 0) { "Amount must be greater than 0" }
    }
}
