package blackjack.domain.participants

import blackjack.domain.result.Outcome

class BettingMoney(private val value: Int) {

    init {
        require(value >= 0) { ERROR_MONEY_NEGATIVE }
        require(value % MONEY_LIMIT_VALUE == 0) { ERROR_MONEY_VALUE }
    }

    fun getProfits(outcome: Outcome): Int = (value * outcome.ratio).toInt()

    companion object {
        private const val MONEY_LIMIT_VALUE = 1000
        private const val ERROR_MONEY_NEGATIVE = "돈은 0원 이상이어야 합니다."
        private const val ERROR_MONEY_VALUE = "돈은 ${MONEY_LIMIT_VALUE}으로 나누어떨어져야 합니다."
    }
}
