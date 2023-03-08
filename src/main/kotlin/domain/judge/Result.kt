package domain.judge

enum class Result(val result: String) {
    WIN("승"),
    DRAW("무"),
    LOSS("패");

    fun reverseResult(): Result = when (this) {
        WIN -> LOSS
        LOSS -> WIN
        else -> DRAW
    }
}
