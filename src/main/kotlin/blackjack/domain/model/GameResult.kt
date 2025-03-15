package blackjack.domain.model

enum class GameResult(val profitRate: Double) {
    WIN(1.0),
    LOSE(-1.0),
    BLACKJACK(0.5),
    PUSH(0.0),
}
