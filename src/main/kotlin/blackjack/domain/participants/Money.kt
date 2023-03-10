package blackjack.domain.participants

@JvmInline
value class Money(val value: Int) {
    init {
        require(value in MIN_MONEY..MAX_MONEY) { ERROR_MONEY_RANGE }
    }

    fun toDouble(): Double = value.toDouble()

    companion object {
        private const val MIN_MONEY = 10
        private const val MAX_MONEY = 1_000_000
        private const val ERROR_MONEY_RANGE = "배팅 금액은 10원 ~ 1,000,000원입니다."
    }
}
