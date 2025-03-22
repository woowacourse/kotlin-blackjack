package blackjack.domain

class Money(val amount: Int) {
    init {
        require(amount > MINIMUM_BETTING_AMOUNT) { ERROR_MINIMUM_BETTING_AMOUNT }
    }

    companion object {
        private const val MINIMUM_BETTING_AMOUNT = 0
        private const val ERROR_MINIMUM_BETTING_AMOUNT = "배팅금은 0보다 커야 합니다"
    }
}