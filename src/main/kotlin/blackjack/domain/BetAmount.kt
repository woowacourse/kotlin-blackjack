package blackjack.domain

class BetAmount(val value: Int) {
    init {
        require(value >= MIN_BET_AMOUNT) { INVALID_BET_AMOUNT_MESSAGE }
    }

    operator fun times(multiplier: Int): Int = value * multiplier

    operator fun times(multiplier: Double): Double = value * multiplier

    companion object {
        private const val MIN_BET_AMOUNT = 0
        private const val INVALID_BET_AMOUNT_MESSAGE = "배팅 금액은 최소 $MIN_BET_AMOUNT 원 이상이어야 합니다."
    }
}
