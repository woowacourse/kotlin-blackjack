package domain.money

@JvmInline
value class Money(val value: Int) {
    init {
        checkMoneyRange()
    }

    private fun checkMoneyRange() {
        require(value in MINIMUM_MONEY..MAXIMUM_MONEY) { ERROR_MONEY_RANGE }
    }

    companion object {
        private const val MINIMUM_MONEY = 10_000
        private const val MAXIMUM_MONEY = 100_000
        private const val ERROR_MONEY_RANGE = "배팅 금액은 ${MINIMUM_MONEY}원 에서 ${MAXIMUM_MONEY}원 이하여야 합니다"
    }
}
