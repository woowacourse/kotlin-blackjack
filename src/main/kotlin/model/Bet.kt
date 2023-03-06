package model

data class Bet(val amount: Int) {
    init {
        require(amount in MIN_AMOUNT..MAX_AMOUNT) { AMOUNT_RANGE_ERROR }
    }

    companion object {
        private const val MIN_AMOUNT = 100
        private const val MAX_AMOUNT = 1000000
        private const val AMOUNT_RANGE_ERROR = "배팅 금액은 100원부터 1000000원까지 가능합니다."
    }
}
