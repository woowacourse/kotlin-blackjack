package blackjack.model

class Profit(bettingMoney: String) {
    val amount: Int

    init {
        requireNotNull(bettingMoney.toIntOrNull()) { "베팅금을 숫자로만 입력해주세요" }
        require(bettingMoney.toInt() > 0) { "베팅금은 0보다 큰 정수로 입력해주세요" }
        amount = bettingMoney.toInt()
    }
}
