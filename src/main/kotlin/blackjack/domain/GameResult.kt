package blackjack.domain

enum class GameResult(
    val rate: Double,
) {
    WIN(1.0),
    WIN_BLACKJACK(1.5),
    LOSE(-1.0),
    LOSE_BLACKJACK(-1.5),
    PUSH(0.0),
}
