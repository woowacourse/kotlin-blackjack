package model.participants

class BetMoney private constructor(val amount: Int) {
    companion object {
        const val ERROR_INVALID_TYPE = "배티 금액은 숫자여야 합니다"
        const val ERROR_INVALID_AMOUNT = "배팅 금액은 0보다 커야 합니다"

        fun fromInput(input: String): BetMoney = input.toInt().validatePositive().run(::BetMoney)

        private fun String.toInt(): Int {
            return this.toIntOrNull() ?: throw IllegalArgumentException(ERROR_INVALID_TYPE)
        }

        private fun Int.validatePositive(): Int {
            require(this > 0) { ERROR_INVALID_AMOUNT }
            return this
        }
    }
}
