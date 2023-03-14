package blackjack.domain.result

enum class Result(val rate: Double) {
    DRAW(0.0),
    LOSE(-1.0),
    WIN(1.0);

    fun reverse(): Result {
        val index = this.ordinal
        return Result.values()[Result.values().size - 1 - index]
    }
}
