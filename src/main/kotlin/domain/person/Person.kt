package domain.person

import domain.card.Card
import domain.card.Cards
import domain.constant.GameState

abstract class Person(value: List<Card>, open val name: String) {
    val cards: Cards = Cards(value)

    fun receiveCard(values: List<Card>) {
        values.forEach { cards.add(it) }
    }

    protected abstract fun checkState(): GameState

    fun isState(state: GameState) = checkState() == state
}
