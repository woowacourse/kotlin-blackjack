package blackjack.domain.state

enum class ResultState(val profitRate: Double) {
    WIN(1.0),
    BLACKJACK_WIN(1.5),
    LOSE(-1.0),
    DRAW(0.0),
}
