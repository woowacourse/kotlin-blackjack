package model.participants

sealed class Answer {
    data object YES : Answer()

    data object NO : Answer()

    companion object {
        const val ERROR_INVALID_FORMAT = "y 또는 n만 입력해주세요"
        private const val POSITIVE = "y"
        private const val NEGATIVE = "n"

        fun fromInput(input: String): Answer {
            return when (input.lowercase()) {
                POSITIVE -> YES
                NEGATIVE -> NO
                else -> throw IllegalArgumentException(ERROR_INVALID_FORMAT)
            }
        }
    }
}
