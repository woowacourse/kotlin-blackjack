package domain.state

import domain.card.Hand
import domain.money.Money

class BlackJack(hand: Hand) : Finished(hand) {
    override fun profit(money: Money) = money.value.toDouble() * BUST_PROFIT_RATE

    companion object {
        private const val BUST_PROFIT_RATE = 1.5
    }
}
