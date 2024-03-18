package blackjack.model

enum class EarningRate(val rate: Double) {
    BLACKJACK(1.5),
    WIN(1.0),
    LOSE(-1.0),
    BURST(-1.0),
    DRAW(0.0),
}
