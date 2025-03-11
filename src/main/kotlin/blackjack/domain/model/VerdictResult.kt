package blackjack.domain.model

enum class VerdictResult {
    WIN,
    LOSE,
    DRAW,
    ;

    fun reverse(): VerdictResult {
        return when (this) {
            WIN -> LOSE
            LOSE -> WIN
            else -> DRAW
        }
    }
}
