package blackjack.domain.participants

class Money(val value: Int) {

    init {
        require(value >= MONEY_LIMIT_VALUE) { ERROR_MONEY_VALUE }
    }
    companion object {
        private const val MONEY_LIMIT_VALUE = 1000
        private const val ERROR_MONEY_VALUE = "돈은 1000원 이상이어야 합니다."
    }
}
