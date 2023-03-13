package model

@JvmInline
value class Money(val value: Long) {
    init {
        require(value >= BETTING_MONEY_MIN) { BETTING_MONEY_MIN_ERROR }
    }

    companion object {
        private const val BETTING_MONEY_MIN = 1000
        private const val BETTING_MONEY_MIN_ERROR = "최소 배팅 금액은 1000입니다"
    }
}
