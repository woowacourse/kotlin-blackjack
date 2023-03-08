package domain.judge

enum class Result(val result: String, val revenue: Double) {
    WIN("승", 1.0),
    DRAW("무", 0.0),
    LOSS("패", -1.0),
    BLACKJACK_WIN("승", 1.5);

    fun reverseResult(): Result = when (this) {
        WIN -> LOSS
        LOSS -> WIN
        BLACKJACK_WIN -> LOSS
        else -> DRAW
    }
}
