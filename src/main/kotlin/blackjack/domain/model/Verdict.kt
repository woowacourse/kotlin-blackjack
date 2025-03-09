package blackjack.domain.model

enum class Verdict {
    WIN,
    LOSE,
    DRAW,
    ;

    fun reverse(): Verdict {
        return when (this) {
            WIN -> LOSE
            LOSE -> WIN
            else -> DRAW
        }
    }
}
