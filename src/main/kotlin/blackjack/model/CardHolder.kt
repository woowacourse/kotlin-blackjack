package blackjack.model

import blackjack.state.State
import blackjack.state.State.Running.Hit

abstract class CardHolder {
    private var _hand: Hand = Hand()
    val hand: Hand
        get() = _hand

    private var _state: State = Hit
    val state: State
        get() = _state

    fun addCard(card: Card) {
        _hand += card
        updateState()
    }

    private fun updateState() {
        _state = hand.determineState()
    }

    fun changeState(state: State) {
        _state = state
    }
}
