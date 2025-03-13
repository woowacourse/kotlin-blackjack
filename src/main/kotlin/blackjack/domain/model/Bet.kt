package blackjack.domain.model

class Bet(val amount: Int) {
    init {
        require(amount >= 0) { ERROR_MESSAGE_BET_NOT_POSITIVE }
    }

    companion object {
        private const val ERROR_MESSAGE_BET_NOT_POSITIVE = "베팅 금액은 0보다 작을 수 없습니다."
    }
}
