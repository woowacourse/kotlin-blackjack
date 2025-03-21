package blackjack.domain.model.participant.bet

@JvmInline
value class BetAmount(
    val value: Int,
) {
    init {
        require(value > MINIMUM_BET_AMOUNT) { ERROR_INVALID_BET_AMOUNT }
    }

    fun calculateProfit(profitRate: ProfitRate): Profit = Profit(profitRate.value * value)

    companion object {
        private const val MINIMUM_BET_AMOUNT = 0

        private const val ERROR_INVALID_BET_AMOUNT = "베팅 금액은 ${MINIMUM_BET_AMOUNT}보다 커야 합니다"
    }
}
