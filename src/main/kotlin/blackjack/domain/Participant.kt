package blackjack.domain

import blackjack.domain.state.State

interface Participant {
    var state: State

    fun receiveCard(card: Card) {
        state = state.draw(card)
    }

    fun isOverCondition(): Boolean
}
