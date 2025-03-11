package blackjack.model

enum class DrawChoice(val answer: String) {
    YES("y"),
    NO("n"),
    ;

    companion object {
        fun from(answer: String): DrawChoice? {
            return entries.find { choice -> choice.answer == answer }
        }
    }
}
