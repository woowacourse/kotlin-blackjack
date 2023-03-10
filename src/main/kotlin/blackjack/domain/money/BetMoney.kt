package blackjack.domain.money

class BetMoney(value: Int) : Money(value) {
    init {
        require(value % BET_AMOUNT_UNIT == 0) { "배팅 금액은 ${BET_AMOUNT_UNIT}원 단위입니다. (현재 입력값 : $value)" }
    }

    companion object {
        private const val BET_AMOUNT_UNIT = 1000
    }
}
