package domain

enum class Answer(val value: String) {
    YES(value = "y"),
    NO(value = "n");

    companion object {
        fun of(input: String): Answer {
            return Answer.values().find { answer ->
                answer.value == input
            } ?: throw IllegalArgumentException(ERROR_ANSWER_INPUT)
        }

        private const val ERROR_ANSWER_INPUT = "[ERROR] 대답은 y 또는 n 만 가능합니다!"
    }
}

