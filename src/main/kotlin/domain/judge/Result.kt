package domain.judge

enum class Result {
    BLACKJACK_WIN,
    WIN,
    DRAW,
    LOSS;

    fun reverseResult(): Result = when (this) {
        WIN, BLACKJACK_WIN -> LOSS
        LOSS -> WIN
        else -> DRAW
    }
}
