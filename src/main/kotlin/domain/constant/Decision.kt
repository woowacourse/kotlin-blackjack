package domain.constant

enum class Decision(val text: String) {
    YES("y"), NO("n")

    ;

    companion object {
        fun of(text: String): Decision? =
            values().find { it.text == text }

        private const val ERROR_NOT_DECISION = "y 또는 n 을 입력해야 합니다."
    }
}
