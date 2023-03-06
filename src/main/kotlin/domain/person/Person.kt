package domain.person

import domain.card.Card
import domain.card.Cards

abstract class Person(open val name: String) {
    val cards = Cards()

    fun receiveCard(vararg card: Card) {
        card.forEach { cards.add(it) }
        checkState()
    }

    protected abstract fun checkState(): GameState

    fun isState(state: GameState) = checkState() == state
}
