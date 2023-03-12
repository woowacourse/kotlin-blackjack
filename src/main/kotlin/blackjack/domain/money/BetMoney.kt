package blackjack.domain.money

class BetMoney(amount: Int) : Money(amount) {
    init {
        require(amount > MINIMUM_AMOUNT) { "금액은 양수여야 합니다. (현재 입력값 : $amount)" }
        require(amount % BET_AMOUNT_UNIT == 0) { "배팅 금액은 ${BET_AMOUNT_UNIT}원 단위입니다. (현재 입력값 : $amount)" }
    }

    companion object {
        private const val MINIMUM_AMOUNT = 0
        private const val BET_AMOUNT_UNIT = 1000
    }
}
