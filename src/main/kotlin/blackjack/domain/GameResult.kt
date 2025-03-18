package blackjack.domain

enum class GameResult(val dividend: Double) {
    WIN(1.0),
    LOSE(-1.0),
    PUSH(0.0),
    BLACKJACK(1.5),
}
