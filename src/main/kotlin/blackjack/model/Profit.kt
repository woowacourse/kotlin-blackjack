package blackjack.model

class Profit(betting: String) {
    val amount: Int

    init {
        requireNotNull(betting.toIntOrNull()) { "베팅금을 숫자로만 입력해주세요" }
        require(betting.toInt() > 0) { "베팅금은 0보다 큰 정수로 입력해주세요" }
        amount = betting.toInt()
    }
}
