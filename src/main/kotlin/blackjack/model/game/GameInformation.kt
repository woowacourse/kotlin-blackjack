package blackjack.model.game

import blackjack.model.card.Card
import blackjack.model.user.Hand

class GameInformation(val hand: Hand = Hand()) {
    private var _state: GameState = GameState.Running.HIT
    val state: GameState
        get() = _state

    fun drawCard(card: Card) {
        hand.drawCard(card)
        _state = state.judgeState(hand)
    }

    fun changeStateToStay() {
        _state = GameState.Finished.STAY
    }
}
