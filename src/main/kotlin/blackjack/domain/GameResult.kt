package blackjack.domain

enum class GameResult(val dividendRate: Double) {
    LOSE(-1.0),
    DRAW(0.0),
    BLACKJACK(1.5),
    WIN(1.0);

    operator fun not(): GameResult {
        return when (this) {
            BLACKJACK, WIN -> LOSE
            LOSE -> WIN
            DRAW -> DRAW
        }
    }
}
