package domain.state.dealerState

import domain.card.Hand
import domain.money.Money
import domain.money.Profit
import domain.state.BlackJack
import domain.state.Bust
import domain.state.State
import domain.state.Stay

class DealerBlackJack(hand: Hand) : BlackJack(hand) {
    override fun profit(other: State, money: Money): Profit {
        return when (other) {
            is BlackJack -> Profit.of(money, LOSE_PROFIT_RATE)
            is Bust -> Profit.of(money)
            is Stay -> Profit.of(money)
            else -> throw IllegalStateException()
        }
    }

    companion object {
        private const val LOSE_PROFIT_RATE = -0.5
    }
}
