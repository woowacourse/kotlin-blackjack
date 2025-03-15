package blackjack.domain.model

class Bet(val amount: Int) {
    init {
        require(amount >= MIN_BET_AMOUNT) { ERROR_MESSAGE_BET_NOT_POSITIVE }
    }

    companion object {
        private const val MIN_BET_AMOUNT = 0
        private const val ERROR_MESSAGE_BET_NOT_POSITIVE = "베팅 금액은 ${MIN_BET_AMOUNT}보다 작을 수 없습니다."
    }
}
