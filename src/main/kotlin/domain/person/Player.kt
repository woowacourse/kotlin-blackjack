package domain.person

import domain.card.Card
import domain.state.FirstTurn
import domain.state.State

class Player(
    override val name: String,
    card1: Card,
    card2: Card,
) : Person() {
    override var state: State = FirstTurn(card1, card2)

    fun toStay() {
        state = state.toStay()
    }
}
