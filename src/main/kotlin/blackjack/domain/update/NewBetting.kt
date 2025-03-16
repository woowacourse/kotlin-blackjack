package blackjack.domain.update

@JvmInline
value class NewBetting(
    val amount: Int,
) {
    init {
        require(amount > 0) { "Amount must be greater than 0" }
    }
}
