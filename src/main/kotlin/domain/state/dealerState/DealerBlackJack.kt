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
            is BlackJack -> Profit(money, LOSE_PROFIT_RATE)
            is Bust -> Profit(money)
            is Stay -> Profit(money)
            else -> throw IllegalStateException()
        }
    }

    companion object {
        private const val LOSE_PROFIT_RATE = -1.5
    }
}
