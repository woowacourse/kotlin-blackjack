package blackjack.model.role

import blackjack.model.result.Money

class Player(override val name: PlayerName, private val money: Money = Money.ZERO) : Role(name) {
    fun calculateProfit(dealer: Dealer): Money = state.calculateProfit(money, dealer.state)
}
