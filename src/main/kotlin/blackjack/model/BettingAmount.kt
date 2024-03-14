package blackjack.model

class BettingAmount(val bettingAmount: Int) {
    init {
        require(bettingAmount > MIN_BETTING_AMOUNT) { ERROR_MESSAGE_INVALID_TING_AMOUNT }
    }

    companion object {
        private const val MIN_BETTING_AMOUNT = 0
        private const val ERROR_MESSAGE_INVALID_TING_AMOUNT = "[ERROR] 베팅 금액을 0원이 넘게 입력해주세요."
    }
}
