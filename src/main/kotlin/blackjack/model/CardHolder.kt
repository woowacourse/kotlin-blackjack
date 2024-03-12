package blackjack.model

import blackjack.state.BlackjackState
import blackjack.state.BlackjackState.Running.Hit

abstract class CardHolder {
    private var _hand: Hand = Hand()
    val hand: Hand
        get() = _hand

    private var _blackjackState: BlackjackState = Hit
    val blackjackState: BlackjackState
        get() = _blackjackState

    fun addCard(card: Card) {
        _hand.plus(card)
        updateState()
    }

    private fun updateState() {
        _blackjackState = hand.determineState()
    }

    fun changeState(blackjackState: BlackjackState) {
        _blackjackState = blackjackState
    }
}
