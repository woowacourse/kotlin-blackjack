package blackjack.model.participant

import blackjack.model.card.Hand
import blackjack.state.State

sealed class Participant(val name: String, protected var state: State) {
    val hand: Hand get() = state.hand

    abstract fun judge(other: Participant): GameResult

    fun sumScore() = state.sumScore()

    fun isBust() = state is State.Finish.Bust

    fun isBlackJack() = state is State.Finish.BlackJack
}
