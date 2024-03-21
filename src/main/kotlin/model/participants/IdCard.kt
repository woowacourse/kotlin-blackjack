package model.participants

@JvmInline
value class IdCard private constructor(val name: String) {
    companion object {
        const val ERROR_INVALID_LENGTH = "이름의 길이는 1~10 이어야 합니다."
        private const val LENGTH_LOWER_BOUND = 1
        private const val LENGTH_UPPER_BOUND = 10

        fun fromInput(input: String): IdCard = input.validateLength().run(::IdCard)

        private fun String.validateLength(): String {
            require(this.length in LENGTH_LOWER_BOUND..LENGTH_UPPER_BOUND) { ERROR_INVALID_LENGTH }
            return this
        }
    }
}
