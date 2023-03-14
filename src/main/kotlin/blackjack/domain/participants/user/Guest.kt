package blackjack.domain.participants.user

import blackjack.domain.state.FirstTurn
import blackjack.domain.state.State

class Guest(
    val bettingMoney: Money = Money(10),
    name: Name = Name("게스트"),
    state: State = FirstTurn(),
) : User(name, state) {
    fun calculateProfit(dealer: Dealer): Int = (bettingMoney.toDouble() * state.matchWith(dealer.state).rate).toInt()
}
