package domain.person

import domain.card.HandOfCards
import domain.state.PlayerFirstTurn
import domain.state.State

class Player(
    override val name: String,
) : Person() {
    override var state: State = PlayerFirstTurn(HandOfCards())

    fun toStay() {
        state = state.toStay()
    }
}
