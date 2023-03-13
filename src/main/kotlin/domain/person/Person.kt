package domain.person

import domain.card.Card
import domain.card.Hand
import domain.result.Score
import domain.state.State

abstract class Person(val name: String) {

    abstract var state: State

    val score: Score
        get() = Score.of(Hand(state.getHandCards()))

    fun receiveCard(cards: List<Card>) {
        cards.forEach { state = state.draw(it) }
    }

    fun stay() {
        state = state.stay()
    }
}
