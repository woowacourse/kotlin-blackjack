package model.participants

@JvmInline
value class Money (val amount: Int = 0) {

    init {
        require(amount >= 0) { ERROR_INVALID_AMOUNT }
    }
    companion object {
        const val ERROR_INVALID_TYPE = "배티 금액은 숫자여야 합니다"
        const val ERROR_INVALID_AMOUNT = "배팅 금액은 0보다 커야 합니다"

        fun fromInput(input: String): Money = input.toInt().run(::Money)

        private fun String.toInt(): Int {
            return this.toIntOrNull() ?: throw IllegalArgumentException(ERROR_INVALID_TYPE)
        }
    }
}
