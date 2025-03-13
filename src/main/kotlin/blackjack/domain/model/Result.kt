package blackjack.domain.model

enum class Result(val profitRate: Double) {
    WIN(1.0),
    LOSE(-1.0),
    PUSH(0.0),
    ;

    fun reverse(): Result {
        return when (this) {
            WIN -> LOSE
            LOSE -> WIN
            else -> PUSH
        }
    }
}
