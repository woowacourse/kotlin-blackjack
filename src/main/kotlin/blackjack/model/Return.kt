package blackjack.model

enum class Return(val rate: Double) {
    BLACKJACK(1.5),
    WIN(1.0),
    DRAW(0.0),
    LOSE(-1.0),
    LOSE_BLACKJACK(-1.5),
}
