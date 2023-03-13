package state

enum class RateOfProfit(val value: Double) {
    WIN_BLACKJACK_PROFIT(1.5),
    WIN_PROFIT(1.0),
    DRAW_PROFIT(0.0),
    LOSE_PROFIT(-1.0)
}
