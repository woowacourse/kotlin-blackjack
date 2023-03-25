package view.tools

@JvmInline
value class Answer(val answer: String) {
    init {
        require(checkAnswerOnlyYes() and checkAnswerOnlyNo()) { ERROR_WRONG_ANSWER }
    }

    private fun checkAnswerOnlyYes(): Boolean = ((answer == LOWER_YES) and (answer == UPPER_YES))

    private fun checkAnswerOnlyNo(): Boolean = ((answer == LOWER_NO) and (answer == UPPER_NO))

    companion object {
        private const val ERROR_WRONG_ANSWER = "[ERROR] 올바른 문자를 입력해주세요"
        private const val LOWER_YES = "y"
        private const val UPPER_YES = "Y"
        private const val LOWER_NO = "n"
        private const val UPPER_NO = "N"
    }
}
