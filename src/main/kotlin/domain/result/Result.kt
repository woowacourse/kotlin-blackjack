package domain.result

import domain.participants.Money

enum class Result(private val profit: Double) {
    WIN(1.0),
    DRAW(0.0),
    LOSS(-1.0);

    fun calculateProfit(money: Money) = money * profit
}
