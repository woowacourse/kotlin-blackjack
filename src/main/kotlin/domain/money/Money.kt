package domain.money

data class Money(val value: Int) {

    init {
        isMoneyOverTen()
    }

    private fun isMoneyOverTen() {
        require(value >= MONEY_MIN) { ERROR_PRINT_MIN_MONEY }
    }

    companion object {
        private const val MONEY_MIN = 10
        private const val ERROR_PRINT_MIN_MONEY = "돈은 최소 10보다 많아야 합니다"
    }
}
