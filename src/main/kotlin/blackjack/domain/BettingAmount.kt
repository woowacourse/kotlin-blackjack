package blackjack.domain

@JvmInline
value class BettingAmount(
    private val value: Int,
) {
    init {
        require(value >= MIN_BETTING_AMOUNT) { ERROR_INVALID_RANGE }
    }

    fun profit(result: GameResult): Profit = Profit(value * result.rate)

    companion object {
        private const val MIN_BETTING_AMOUNT = 0
        private const val ERROR_INVALID_RANGE = "배팅 금액은 0 이상이어야 합니다."
    }
}
