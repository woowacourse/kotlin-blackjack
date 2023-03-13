package domain.person

import domain.card.Card
import domain.state.Bust
import domain.state.InProgress
import domain.state.State

abstract class Person {
    abstract val name: String
    abstract var state: State
        protected set

    fun toNextState(card: Card) {
        state = state.nextState(card)
    }

    fun showHandOfCards(): List<Card> = state.handOfCards.cards

    fun getTotal(): Int = state.handOfCards.getTotalCardSum()

    fun isBust() = state is Bust
    fun isInProgress() = state is InProgress
}
