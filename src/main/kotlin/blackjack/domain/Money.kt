package blackjack.domain

@JvmInline
value class Money(
    val value: Int,
) {
    init {
        require(value >= MINIMUM_BETTING_MONEY) { ERROR_UNDER_MINIMUM_BETTING_MONEY_MESSAGE }
    }

    companion object {
        const val ERROR_UNDER_MINIMUM_BETTING_MONEY_MESSAGE = "[ERROR] 최소 금액은 1000원입니다."
        const val MINIMUM_BETTING_MONEY = 1000
    }
}
