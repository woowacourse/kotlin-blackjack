package blackjack.model

enum class DrawChoice(val answer: String) {
    YES("y"),
    NO("n"),
    ;

    fun isStay(): Boolean {
        return this == NO
    }

    companion object {
        fun from(answer: String): DrawChoice? {
            return entries.find { choice -> choice.answer == answer }
        }
    }
}
