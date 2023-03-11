package blackjack.domain.participants.user

import blackjack.domain.state.State
import blackjack.domain.state.inTurn.FirstTurn

class Dealer(
    name: Name = Name("딜러"),
    state: State = FirstTurn(),
) : User(name, state) {
    override val isContinuable: Boolean
        get() = state.score.value < DEALER_MIN_NUMBER

    fun calculateProfit(guests: List<Guest>): Int = guests.sumOf { it.calculateProfit(this) } * -1

    companion object {
        private const val DEALER_MIN_NUMBER = 17
    }
}
