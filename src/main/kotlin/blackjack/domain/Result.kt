package blackjack.domain

enum class Result(val word: String) {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    fun reverse(): Result {
        val index = this.ordinal
        return Result.values()[Result.values().size - 1 - index]
    }
}
