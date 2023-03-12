package blackjack.domain.result

enum class GameResult(val ratio: Float) {
    WIN(2.0f),
    BLACKJACK(1.5f),
    DRAW(1.0f),
    LOSE(0.0f),
}
