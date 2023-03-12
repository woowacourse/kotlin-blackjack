package blackjack.domain.money

class BetMoney(private val money: Money = Money()) {
    init {
        val amount = money.getAmount()
        require(amount > MINIMUM_AMOUNT) { "금액은 양수여야 합니다. (금액 : $amount)" }
        require(amount % BET_AMOUNT_UNIT == 0) { "배팅 금액은 ${BET_AMOUNT_UNIT}원 단위입니다. (금액 : $amount)" }
    }

    fun getAmount(): Int = money.getAmount()

    operator fun times(operand: Double): BetMoney = BetMoney(money * operand)

    operator fun plus(operand: Money): BetMoney = BetMoney(money + operand)

    companion object {
        private const val MINIMUM_AMOUNT = 0
        private const val BET_AMOUNT_UNIT = 1000
    }
}
