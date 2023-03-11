package blackjack.domain.participants.user

import blackjack.domain.state.State
import blackjack.domain.state.inTurn.FirstTurn
import blackjack.domain.state.inTurn.Hit

class Guest(
    val bettingMoney: Money = Money(10),
    name: Name = Name("게스트"),
    state: State = FirstTurn(),
) : User(name, state) {
    override val isContinuable: Boolean
        get() = state is Hit || state is FirstTurn

    fun calculateProfit(dealer: Dealer): Int = (bettingMoney.toDouble() * state.matchWith(dealer.state).rate).toInt()
}
