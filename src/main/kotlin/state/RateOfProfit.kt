package state

enum class RateOfProfit(val value: Double) {
    WIN_BLACKJACK(1.5),
    WIN(1.0),
    DRAW(0.0),
    LOSE(-1.0)
}
