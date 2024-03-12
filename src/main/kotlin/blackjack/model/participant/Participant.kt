package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.Hand
import blackjack.state.State

sealed class Participant(val name: String, protected var state: State) {
    val hand: Hand get() = state.hand
    val ratePoint: Int get() = state.ratePoint

    abstract fun play(
        onDraw: () -> Card,
        onDone: (Participant) -> Unit,
    )

    abstract fun judge(other: Participant): GameResult

    fun sumScore() = state.sumScore()
}
