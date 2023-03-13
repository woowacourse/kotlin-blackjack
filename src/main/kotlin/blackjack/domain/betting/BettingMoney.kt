package blackjack.domain.betting

class BettingMoney(private val money: Int) {
    init {
        require(money >= MINIMUM_MONEY) { ERROR_MONEY }
    }

    operator fun times(d: Double): Int {
        return (money * d).toInt()
    }

    companion object {
        private const val MINIMUM_MONEY: Int = 100
        private const val ERROR_MONEY = "최소 금액인 ${MINIMUM_MONEY}원 이상을 입력해주세요."
    }
}
