package domain.state.dealerState

import domain.card.Hand
import domain.money.Money
import domain.money.Profit
import domain.state.Stay

class DealerStay(hand: Hand) : Stay(hand) {
    override fun getLoseProfit(money: Money) = Profit.of(money, LOSE_PROFIT_RATE)

    companion object {
        private const val LOSE_PROFIT_RATE = -0.5
    }
}
