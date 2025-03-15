package blackjack.domain.model

enum class MatchResult(val profitRate: Double) {
    WIN(1.0),
    LOSE(-1.0),
    DRAW(0.0),
    BLACKJACK(1.5),
    BLACKJACK_LOSE(-1.5),
    ;

    fun reverse(): MatchResult {
        return when (this) {
            WIN -> LOSE
            LOSE -> WIN
            DRAW -> DRAW
            BLACKJACK -> BLACKJACK_LOSE
            BLACKJACK_LOSE -> BLACKJACK
        }
    }
}
