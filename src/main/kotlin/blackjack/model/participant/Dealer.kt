package blackjack.model.participant

import blackjack.model.state.StartGame
import blackjack.model.state.State

class Dealer(
    name: String = DEALER,
    state: State = StartGame(),
) : Participant(name, state) {
    fun canDraw(): Boolean = state.hand.score() <= DEALER_HIT_SCORE

    companion object {
        const val DEALER = "딜러"
        const val DEALER_HIT_SCORE = 16
    }
}
