package blackjack.domain

enum class Consequence(val yieldRate: Double) {
    WIN(1.0),
    LOSE(-1.0),
    DRAW(0.0),
}
