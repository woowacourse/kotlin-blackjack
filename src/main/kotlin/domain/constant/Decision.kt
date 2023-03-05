package domain.constant

enum class Decision(val text: String) {
    YES("y"),
    NO("n"),
    ;

    companion object {
        fun of(text: String): Decision? =
            values().find { it.text == text }
    }
}
