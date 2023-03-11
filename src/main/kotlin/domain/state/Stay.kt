package domain.state

import domain.card.Hand
import domain.money.Money

class Stay(hand: Hand) : Finished(hand) {
    override fun profit(money: Money) = money.value.toDouble() * STAY_PROFIT_RATE
    companion object {
        private const val STAY_PROFIT_RATE = 1
    }
}
