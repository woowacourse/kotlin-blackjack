package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.state.PlayingState
import blackjack.domain.state.Ready

abstract class Participant(val name: String) {
    var state: PlayingState = Ready()
        private set

    fun drawTo(card: Card) {
        state = state.draw(card)
    }

    fun profit(other: Participant) {
        val stateProfit = state.profit(other.state)
        println("$stateProfit")
        return calculateProfit(stateProfit)
    }

    fun changeState(newState: PlayingState) {
        state = newState
    }

    abstract fun calculateProfit(stateProfit: Double)

    abstract fun canDraw(): Boolean

    abstract fun stay()
}
