package domain.judge

import kotlin.math.roundToInt

enum class Result(val result: String, val profit: Double) {
    WIN("승", 1.0),
    DRAW("무", 0.0),
    LOSS("패", -1.0),
    BLACKJACK_WIN("승", 1.5);

    fun reverseResult(): Result = when (this) {
        WIN -> LOSS
        LOSS -> WIN
        BLACKJACK_WIN -> LOSS
        else -> DRAW
    }

    fun calculateProfit(money: Int): Int {
        return (this.profit * money).roundToInt()
    }
}
