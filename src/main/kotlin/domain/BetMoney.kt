package domain

class BetMoney(val value: Int) {
    init {
        check(value in MINIMUM_BET_RANGE..MAXIMUM_BET_RANGE) { ERROR_BET_MONEY_RANGE }
    }

    companion object {
        private const val MINIMUM_BET_RANGE = 1000
        private const val MAXIMUM_BET_RANGE = 10000000
        private const val ERROR_BET_MONEY_RANGE = "[ERROR] 베팅 금액은 최소 1000원 최대 1000만원입니다."
    }
}
