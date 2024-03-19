package blackjack.constants

enum class GameResult(val profitRate: Double) {
    BLACKJACK_WIN(1.5),
    BLACKJACK_LOSE(-1.5),
    WIN(1.0),
    DRAW(0.0),
    LOSE(-1.0),
}
