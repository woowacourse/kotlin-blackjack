package blackjack.model.amount

@JvmInline
value class BetAmount(val amount: Int) {
    init {
        require(amount > 0) { ERROR_BET_AMOUNT_VALUE }
    }

    companion object {
        private const val ERROR_BET_AMOUNT_VALUE = "배팅 금액은 1원 이상이어야 합니다."
    }
}
