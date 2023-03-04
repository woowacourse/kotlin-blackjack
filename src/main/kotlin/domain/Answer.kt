package domain

class Answer(
    val value: String,
) {
    init {
        require((value == YES) or (value == NO)) {
            ERROR_ANSWER_FORMAT
        }
    }

    companion object {
        const val YES = "y"
        const val NO = "n"
        private const val ERROR_ANSWER_FORMAT = "[ERROR] 올바른 입력이 아닙니다."
    }
}
