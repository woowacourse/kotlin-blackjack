package blackjack.domain.model

enum class Result {
    WIN,
    LOSE,
    DRAW,
    ;

    fun reverse(): Result {
        return when (this) {
            WIN -> LOSE
            LOSE -> WIN
            else -> DRAW
        }
    }
}
