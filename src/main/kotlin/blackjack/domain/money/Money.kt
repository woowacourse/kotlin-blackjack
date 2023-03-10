package blackjack.domain.money

import blackjack.isBiggerThan

abstract class Money(val value: Int) {
    init {
        require(value.isBiggerThan(MINIMUM_AMOUNT)) { "금액은 양수여야 합니다. (현재 입력값 : $value)" }
    }

    abstract operator fun times(operand: Double): Money

    companion object {
        private const val MINIMUM_AMOUNT = 0
    }
}
