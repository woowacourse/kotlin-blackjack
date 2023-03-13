package domain.judge

enum class Result {
    WIN,
    DRAW,
    LOSS;

    fun reverseResult(): Result = when (this) {
        WIN -> LOSS
        LOSS -> WIN
        else -> DRAW
    }
}
