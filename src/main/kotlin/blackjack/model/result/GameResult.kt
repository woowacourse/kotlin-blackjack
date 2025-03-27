package blackjack.model.result

enum class GameResult(
    val profit: Float,
) {
    PUSH(0f),
    WIN(1f),
    LOSE(-1f),
}
