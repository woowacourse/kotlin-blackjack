package blackjack.domain.participants

import blackjack.domain.state.FirstTurn
import blackjack.domain.state.State

class Dealer(
    name: Name = Name("딜러"),
    state: State = FirstTurn(),
) : User(name, state) {
    override val isContinuable: Boolean
        get() = state.score.value < DEALER_MIN_NUMBER

    fun calculateProfit(guests: List<Guest>): Int {
        return -guests.sumOf { it.calculateProfit(this) }
    }

    companion object {
        private const val DEALER_MIN_NUMBER = 17
    }
}
