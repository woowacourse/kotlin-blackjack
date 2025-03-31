package blackjack.domain

import blackjack.domain.state.Ready
import blackjack.domain.state.State

abstract class Participant {
    var state: State = Ready()

    fun drawCard(card: Card) {
        state = state.draw(card)
    }

    abstract fun drawMoreCard(): Boolean

    abstract fun stay()
}
