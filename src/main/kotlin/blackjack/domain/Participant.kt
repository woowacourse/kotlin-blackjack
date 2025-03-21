package blackjack.domain

import blackjack.domain.state.PlayingState
import blackjack.domain.state.Ready

abstract class Participant(val name: String) {
    var state: PlayingState = Ready()

    fun profit(other: Participant) {
        val stateProfit = state.profit(other.state)
        return calculateProfit(stateProfit)
    }

    abstract fun calculateProfit(stateProfit: Double)
}
