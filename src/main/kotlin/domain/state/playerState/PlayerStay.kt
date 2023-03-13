package domain.state.playerState

import domain.card.Hand
import domain.money.Money
import domain.money.Profit
import domain.state.Stay

class PlayerStay(hand: Hand) : Stay(hand) {
    override fun getLoseProfit(money: Money) = Profit.of(money, LOSE_PROFIT_RATE)

    companion object {
        private const val LOSE_PROFIT_RATE = -1.0
    }
}
