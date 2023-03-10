package blackjack.domain.participants

import blackjack.domain.result.Outcome

class Money(val value: Int) {

    init {
        require(value >= MONEY_LIMIT_VALUE) { ERROR_MONEY_LIMIT_VALUE }
        require(value % MONEY_LIMIT_VALUE == 0) { ERROR_MONEY_VALUE }
    }

    fun getProfits(outcome: Outcome): Int {
        return value + (value * outcome.ratio).toInt()
    }
    // operator fun plus(other: Money): Money = Money(value + other.value)

    companion object {
        private const val MONEY_LIMIT_VALUE = 1000
        private const val ERROR_MONEY_LIMIT_VALUE = "돈은 ${MONEY_LIMIT_VALUE}원 이상이어야 합니다."
        private const val ERROR_MONEY_VALUE = "돈은 ${MONEY_LIMIT_VALUE}으로 나누어떨어져야 합니다."
    }
}
