package blackjack.model

class BettingAmount(private val amount: Int) {
    init {
        require(amount >= BETTING_AMOUNT_MIN) { ERROR_BETTING_AMOUNT_RANGE }
    }

    fun getAmount(): Int = amount

    companion object {
        private const val BETTING_AMOUNT_MIN = 1000
        private const val ERROR_BETTING_AMOUNT_RANGE = "베팅 금액은 ${BETTING_AMOUNT_MIN}원 이상이어야 합니다."
        private const val ERROR_BETTING_AMOUNT_TYPE = "베팅 금액은 숫자여야 합니다."

        fun bettingAmountOf(inputAmount: String): BettingAmount {
            val amount = validateType(inputAmount)
            return BettingAmount(amount)
        }

        private fun validateType(inputAmount: String): Int {
            try {
                return inputAmount.toInt()
            } catch (e: NumberFormatException) {
                throw IllegalArgumentException(ERROR_BETTING_AMOUNT_TYPE)
            }
        }
    }
}
