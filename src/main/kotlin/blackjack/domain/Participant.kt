package blackjack.domain

import blackjack.domain.state.*

abstract class Participant(name: ParticipantName) {
    private val _name = name
    val name = _name.toString()
    protected abstract var state: State

    fun getHand(): List<Card> = state.hand.toList()

    open fun receive(card: Card) {
        state = state.draw(card)
    }

    fun getScore(): Int = state.getScore()

    fun isBust(): Boolean = state is Bust

    fun isFinished(): Boolean = state is Finished
}
