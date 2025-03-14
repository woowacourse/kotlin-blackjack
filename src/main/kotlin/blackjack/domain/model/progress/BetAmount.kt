package blackjack.domain.model.progress

@JvmInline
value class BetAmount(
    val betAmount: Int,
) {
    init {
        require(betAmount > 0) { ERROR_INVALID_BET_AMOUNT }
    }

    companion object {
        private const val ERROR_INVALID_BET_AMOUNT = "베팅 금액은 0보다 커야 합니다"
    }
}
