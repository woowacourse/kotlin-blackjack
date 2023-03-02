package domain

class Answer(
    value: String,
) {
    init {
        require((value == YES) or (value == NO)) {
            ERROR_ANSWER_FORMAT
        }
    }

    companion object {
        private const val YES = "y"
        private const val NO = "n"
        private const val ERROR_ANSWER_FORMAT = "[ERROR] 올바른 입력이 아닙니다."
    }
}
