package blackjack.domain.participants

import blackjack.domain.state.FirstTurn
import blackjack.domain.state.State

class Guest(
    name: Name,
    state: State = FirstTurn(),
    val bettingMoney: Money = Money(10),
) : User(name, state) {
    override val isContinuable: Boolean
        get() = isBust.not() && isBlackJack.not()
}
