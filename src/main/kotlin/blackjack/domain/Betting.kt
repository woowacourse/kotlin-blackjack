package blackjack.domain

@JvmInline
value class Betting(
    val amount: Int,
) {
    init {
        require(amount > 0) { "Amount must be greater than 0" }
    }
}
