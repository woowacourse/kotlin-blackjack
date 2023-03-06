package blackjack.domain.participants

@JvmInline
value class Money(private val money: Int) {
    init {
        require(money in MIN_MONEY..MAX_MONEY) { ERROR_MONEY_RANGE }
    }

    fun toInt() = money

    companion object {
        private const val MIN_MONEY = 10
        private const val MAX_MONEY = 1_000_000
        private const val ERROR_MONEY_RANGE = "배팅 금액은 10원 ~ 1,000,000원입니다."
    }
}
