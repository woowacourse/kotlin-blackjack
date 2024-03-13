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

    private fun changeState(blackjackState: BlackjackState) {
        _blackjackState = blackjackState
    }

    fun drawCard(shouldDrawCard: () -> Boolean) {
        while (blackjackState is Hit) {
            drawDecision(shouldDrawCard)
        }
    }

    private fun drawDecision(shouldDrawCard: () -> Boolean) {
        if (shouldDrawCard()) {
            addCard(card = GameDeck.drawCard())
        } else {
            changeState(blackjackState = BlackjackState.Finished.Stay)
        }
    }
}
