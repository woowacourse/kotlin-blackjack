package blackjack.model

class Profit(bettingMoney: String) {
    private var _amount = 0
    val amount: Int
        get() = _amount

    init {
        requireNotNull(bettingMoney.toIntOrNull()) { "베팅금을 숫자로만 입력해주세요" }
        require(bettingMoney.toInt() > 0) { "베팅금은 0보다 큰 정수로 입력해주세요" }
        _amount = bettingMoney.toInt()
    }

    fun giveBackBettingMoney() {
        _amount = 0
    }
}
