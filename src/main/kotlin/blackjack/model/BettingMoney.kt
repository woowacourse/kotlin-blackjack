package blackjack.model

data class BettingMoney(val money: Double) {
    init {
        require(money in MIN_MONEY..MAX_MONEY) {
            ERROR_OUT_OF_RANGE_BETTING_MONEY
        }
    }

    operator fun times(earningRate: Double): Double = money * earningRate

    operator fun unaryMinus(): Double = -money

    companion object {
        const val MIN_MONEY: Double = 0.0
        const val MAX_MONEY: Double = 10_000_000.0
        private const val ERROR_OUT_OF_RANGE_BETTING_MONEY = "배팅 금액은 ${MIN_MONEY}에서 ${MAX_MONEY}사이의 숫자입니다."
    }
}
