package blackjack.domain

@JvmInline
value class Money(private val amount: Int) {
    init {
        require(amount.isNotNegative()) { MONEY_AMOUNT_NEGATIVE_ERROR }
        require(amount % 10 == 0) { MONEY_AMOUNT_NOT_DIVIDABLE_BY_10_ERROR }
        require(amount <= MAX_AMOUNT) { OVER_MAX_AMOUNT_ERROR.format(MAX_AMOUNT, amount) }
    }

    private fun Int.isNotNegative(): Boolean = this >= 0
    fun toInt(): Int = amount

    companion object {
        private const val MAX_AMOUNT = 100_000_000
        private const val MONEY_AMOUNT_NEGATIVE_ERROR = "돈의 금액은 음수일 수 없습니다."
        private const val MONEY_AMOUNT_NOT_DIVIDABLE_BY_10_ERROR = "돈의 금액은 10으로 나누어 떨어져야 합니다."
        private const val OVER_MAX_AMOUNT_ERROR = "돈의 금액은 최대 %d 입니다."
    }
}
