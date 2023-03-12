package blackjack.domain.result

enum class GameResult(val ratio: Float) {
    WIN(1.0f),
    BLACKJACK(0.5f),
    DRAW(0.0f),
    LOSE(-1.0f),
}
