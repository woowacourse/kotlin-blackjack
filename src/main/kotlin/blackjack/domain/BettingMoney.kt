package blackjack.domain

class BettingMoney(private val money: Int) {
    init {
        require(money > MIN_MONEY) { ERROR_MONEY }
    }

    fun multipleOnePointFive(): Int = (money * 1.5).toInt()

    fun multipleOne(): Int = money

    fun multipleTwo(): Int = money * 2

    fun multipleZero(): Int = 0

    fun toInt(): Int = money

    companion object {
        private const val MIN_MONEY = 0
        private const val ERROR_MONEY = "베팅 금액은 최소 금액은 0원보다 커야합니다."
    }
}
