package blackjack

enum class DrawChoice(val answer: String) {
    YES("y"),
    NO("n"),
    ;

    companion object {
        fun from(answer: String): DrawChoice {
            return entries.first { choice -> choice.answer == answer }
        }

        fun contains(answer: String): Boolean {
            return entries.any { choice -> choice.answer == answer }
        }
    }
}