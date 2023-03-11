package blackjack.domain.money

import blackjack.isGreaterThan

open class Money(private val value: Int) {
    init {
        require(value.isGreaterThan(MINIMUM_AMOUNT)) { "금액은 양수여야 합니다. (현재 입력값 : $value)" }
    }

    operator fun times(operand: Double): Money = Money((value * operand).toInt())

    companion object {
        private const val MINIMUM_AMOUNT = 0
    }
}
