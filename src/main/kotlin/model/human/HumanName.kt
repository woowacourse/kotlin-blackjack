package model.human

class HumanName private constructor(val name: String) {
    companion object {
        const val ERROR_INVALID_LENGTH = "이름의 길이는 1~10 이어야 합니다."

        fun fromInput(input: String): HumanName = input.validateLength().run(::HumanName)

        private fun String.validateLength(): String {
            require(this.length in 1..10) { ERROR_INVALID_LENGTH }
            return this
        }
    }
}
