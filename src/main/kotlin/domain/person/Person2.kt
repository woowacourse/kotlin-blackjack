package domain.person

import domain.card.Card
import domain.state.Finished
import domain.state.Started
import domain.state.State

abstract class Person2(val name: String) {

    abstract var state: State

    fun isFinished() = state is Finished

    fun isStarted() = state is Started

    fun receiveCard(cards: List<Card>) {
        cards.forEach { state = state.draw(it) }
    }

    fun getHandCards() = state.getHandCards()
}
