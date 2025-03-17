package blackjack.domain.model

class BetAmount(private val money: Int) {
    init {
        require(money >= MINIMUM_BET_AMOUNT) { INVALID_BET_AMOUNT.format(MINIMUM_BET_AMOUNT) }
    }

    operator fun times(dividend: Double): Int {
        return (money * dividend).toInt()
    }

    companion object {
        private const val MINIMUM_BET_AMOUNT: Int = 0

        private const val INVALID_BET_AMOUNT: String = "[ERROR] 베팅 금액은 %d 이상이어야 합니다"
    }
}
