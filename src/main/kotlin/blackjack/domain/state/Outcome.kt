package blackjack.domain.state

enum class Outcome(val rate: Double) {
    WIN_WITH_BLACKJACK(0.5),
    WIN(1.0),
    DRAW(0.0),
    LOSE(-1.0),
}
