package domain.state

import domain.card.Card

interface State {
    val isFinished: Boolean
    fun nextState(draw: () -> Card): State
    fun toStay(): State
    fun profit(): Double
}
