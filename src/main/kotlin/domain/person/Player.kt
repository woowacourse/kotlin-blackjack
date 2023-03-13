package domain.person

import domain.card.HandOfCards
import domain.state.FirstTurn
import domain.state.State

class Player(
    override val name: String,
) : Person() {
    override var state: State = FirstTurn(HandOfCards())

    fun toStay() {
        state = state.toStay()
    }
}
