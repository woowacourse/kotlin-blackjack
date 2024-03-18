package model.human

data class HumanName(val name: String) {
    init {
        require(name.length in NAME_LENGTH_RANGE) { ERROR_INVALID_LENGTH }
    }

    companion object {
        private const val ERROR_INVALID_LENGTH = "이름의 길이는 1 이상 10 이하 이어야 합니다."
        private const val MIN_NAME_LENGTH: Int = 1
        private const val MAX_NAME_LENGTH: Int = 10
        private val NAME_LENGTH_RANGE: IntRange = MIN_NAME_LENGTH..MAX_NAME_LENGTH
    }
}
