package blackjack.model

data class BettingMoney(val money: Int?) {
    init {
        require(money in MIN_MONEY..MAX_MONEY) {
            ERROR_OUT_OF_RANGE_BETTING_MONEY
        }
    }

    companion object {
        const val MIN_MONEY: Int = 0
        const val MAX_MONEY: Int = 10_000_000
        private const val ERROR_OUT_OF_RANGE_BETTING_MONEY = "배팅 금액은 ${MIN_MONEY}에서 ${MAX_MONEY}사이의 숫자입니다."
    }
}
