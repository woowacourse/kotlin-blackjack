package blackjack.model.domain

@JvmInline
value class BettingMoney(val amount: Float) {
    init {
        require(amount > 0) { ERROR_INVALID_BETTING_MONEY }
    }

    companion object {
        private const val ERROR_INVALID_BETTING_MONEY: String = "베팅 금액은 양수만 입력가능합니다."
    }
}
