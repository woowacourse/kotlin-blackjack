package blackjack.domain.participants.user

import blackjack.domain.state.State
import blackjack.domain.state.inTurn.FirstTurn
import blackjack.domain.state.inTurn.Hit

class Guest(
    name: Name,
    state: State = FirstTurn(),
    val bettingMoney: Money = Money(10),
) : User(name, state) {
    override val isContinuable: Boolean
        get() = state is Hit || state is FirstTurn

    fun calculateProfit(dealer: Dealer): Int {
        return (bettingMoney.toDouble() * state.matchWith(dealer).rate).toInt()
    }
}
