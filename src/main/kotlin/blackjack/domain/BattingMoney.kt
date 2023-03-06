package blackjack.domain

class BattingMoney(value: Int) {

    var value = value
        private set

    init {
        require(value > 0) {
            BATTING_MONEY_EXCEPTION
        }
    }

    companion object {
        private const val BATTING_MONEY_EXCEPTION = "[ERROR] 배팅 금액은 0원보다 커야합니다."
    }
}
