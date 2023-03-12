package domain.state

import domain.card.Hand
import domain.money.Money
import domain.money.Profit

class Bust(hand: Hand) : Finished(hand) {
    override fun profit(other: State, money: Money): Profit {
        if (other is Bust) return Profit(money, DRAW_PROFIT_RATE)
        return Profit(money, LOSE_PROFIT_RATE)
    }

    companion object {
        private const val LOSE_PROFIT_RATE = -1.0
        private const val DRAW_PROFIT_RATE = 0.0
    }
}
