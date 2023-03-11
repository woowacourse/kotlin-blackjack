package blackjack.domain

class BettingMoney(private val money: Int) {
    init {
        require(money > MIN_MONEY) { ERROR_MONEY }
    }

    operator fun times(d: Double): Int = (money * d).toInt()

    fun minusFrom(profit: Int): Int = profit - money

    companion object {
        private const val MIN_MONEY = 0
        private const val ERROR_MONEY = "베팅 금액은 최소 금액은 0원보다 커야합니다."
    }
}
