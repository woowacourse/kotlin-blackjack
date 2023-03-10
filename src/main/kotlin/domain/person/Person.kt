package domain.person

import domain.card.Card
import domain.card.HandOfCards

abstract class Person() {
    abstract val name: String
    protected abstract val handOfCards: HandOfCards

    fun receiveCard(vararg card: Card) {
        card.forEach { handOfCards.addCard(it) }
    }

    fun showHandOfCards(): List<Card> = state.handOfCards.cards

    fun getTotal(): Int = state.handOfCards.getTotalCardSum()
}
