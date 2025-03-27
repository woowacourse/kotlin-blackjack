package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.state.StartGame
import blackjack.model.state.State

abstract class Participant(
    val name: String,
    state: State = StartGame(),
) {
    var state: State = state
        private set

    fun receiveCard(card: Card) {
        state = this.state.draw(card)
    }

    fun stop() {
        state = this.state.stop()
    }
}
