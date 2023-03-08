package domain

class BettingMoney(val money: Int) {
    init {
        require(money >= MINIMUM_MONEY) { MINIMUM_MONEY_ERROR_MESSAGE }
    }

    companion object {
        private const val MINIMUM_MONEY = 0
        private const val MINIMUM_MONEY_ERROR_MESSAGE = "[ERROR] 베팅 금액은 음수가 되면 안됩니다."
    }
}
