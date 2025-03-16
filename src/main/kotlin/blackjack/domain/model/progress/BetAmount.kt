package blackjack.domain.model.progress

@JvmInline
value class BetAmount(
    val value: Int,
) {
    init {
        require(value > MINIMUM_BET_AMOUNT) { ERROR_INVALID_BET_AMOUNT }
    }

    constructor() : this(DEFAULT_BET_AMOUNT)

    companion object {
        private const val MINIMUM_BET_AMOUNT = 0
        private const val DEFAULT_BET_AMOUNT = 1

        private const val ERROR_INVALID_BET_AMOUNT = "베팅 금액은 0보다 커야 합니다"
    }
}
