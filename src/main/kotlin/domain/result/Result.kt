package domain.result

import kotlin.math.roundToInt

enum class Result(private val profit: Double) {
    WIN(1.0),
    DRAW(0.0),
    LOSS(-1.0),
    BLACKJACK_WIN(1.5);

    fun calculateProfit(money: Int): Int {
        return (this.profit * money).roundToInt()
    }
}
