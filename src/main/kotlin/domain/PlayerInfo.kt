package domain

data class PlayerInfo(val name: Name, val betMoney: Int) {
    init {
        check(betMoney > MIN_MONEY_BOUNDARY) { ERROR_BET_MONEY_RANGE }
    }

    companion object {
        private const val MIN_MONEY_BOUNDARY = 0
        private const val ERROR_BET_MONEY_RANGE = "[ERROR] 베팅 금액은 ${MIN_MONEY_BOUNDARY}보다 커야 합니다."
    }
}
