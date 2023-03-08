package domain

data class NameAndBet(val name: Name, val betMoney: Int) {
    init {
        check(betMoney >= MIN_MONEY) { ERROR_BET_MONEY_RANGE }
    }

    companion object {
        private const val MIN_MONEY = 0
        private const val ERROR_BET_MONEY_RANGE = "[ERROR] 베팅 금액은 음수일 수 없습니다."
    }
}
