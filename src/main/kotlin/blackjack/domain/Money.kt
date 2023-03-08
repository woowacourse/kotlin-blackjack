package blackjack.domain

@JvmInline
value class Money(private val amount: Int) {
    init {
        require(amount.isNotNegative()) { MONEY_AMOUNT_NEGATIVE_ERROR }
    }

    private fun Int.isNotNegative(): Boolean = this >= 0
    fun toInt(): Int = amount

    companion object {
        private const val MONEY_AMOUNT_NEGATIVE_ERROR = "돈의 금액은 음수일 수 없습니다."
    }
}
