package domain.participants

import kotlin.math.roundToInt

data class Money(val money: Int) {
    init {
        require(money != 0) { ERROR_NO_MONEY }
    }

    operator fun times(ratio: Double): Int = (money * ratio).roundToInt()

    companion object {
        private const val ERROR_NO_MONEY = "[ERROR] 0원 이상의 금액을 입력하세요"
    }
}
