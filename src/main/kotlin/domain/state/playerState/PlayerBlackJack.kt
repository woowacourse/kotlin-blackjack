package domain.state.playerState

import domain.card.Hand
import domain.money.Money
import domain.money.Profit
import domain.state.BlackJack
import domain.state.State

class PlayerBlackJack(hand: Hand) : BlackJack(hand) {
    override fun profit(other: State, money: Money) = Profit.of(money, BLACKJACK_PROFIT_RATE)

    companion object {
        private const val BLACKJACK_PROFIT_RATE = 0.5
    }
}
