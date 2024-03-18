package blackjack.model

class Profit {
    private var _amount = INITIAL_AMOUNT
    val amount: Double
        get() = _amount

    fun initBettingMoney(bettingMoney: String) {
        validate(bettingMoney)
        _amount = bettingMoney.toDouble()
    }

    private fun validate(bettingMoney: String) {
        requireNotNull(bettingMoney.toDoubleOrNull()) { "베팅금을 숫자로만 입력해주세요" }
        require(bettingMoney.toDouble() > INITIAL_AMOUNT) { "베팅금은 0보다 큰 정수로 입력해주세요" }
    }

    fun giveBackBettingMoney() {
        _amount = INITIAL_AMOUNT
    }

    fun lostAllBettingMoney() {
        _amount = -amount
    }

    fun earnProfitForBlackJack() {
        _amount = amount * BLACKJACK_ODDS
    }

    fun calculateProfitByOpponent(opponentProfit: Double) {
        _amount -= opponentProfit
    }

    companion object {
        const val INITIAL_AMOUNT = 0.0
        const val BLACKJACK_ODDS = 1.5
    }
}
