package blackjack.model

@JvmInline
value class BetAmount(private val amount: Double) {
    init {
        require(amount > 0) { INVALID_BET_AMOUNT_ERROR_MESSAGE }
    }

    operator fun times(earningRate: Double): Double = amount * earningRate

    operator fun unaryMinus(): Double = -amount

    companion object {
        private const val INVALID_BET_AMOUNT_ERROR_MESSAGE = "배팅 금액은 0보다 큰 숫자이어야 합니다."
    }
}
