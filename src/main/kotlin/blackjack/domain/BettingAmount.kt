package blackjack.domain

@JvmInline
value class BettingAmount(val value: Int) {
    init {
        require(value > 0) { INITIAL_CARD_COUNT }
    }

    fun calc(dividend: Double): Double {
        return value * dividend
    }

    companion object {
        private const val INITIAL_CARD_COUNT = "베팅 금액은 0원 이상 입력해주세요."
    }
}
