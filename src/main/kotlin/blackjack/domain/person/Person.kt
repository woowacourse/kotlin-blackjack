package blackjack.domain.person

import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.state.PersonState

abstract class Person(
    hand: Hand,
) {
    protected lateinit var state: PersonState
    val gameState: PersonState get() = state

    protected val hand = hand.copy()

    open fun draw(deck: Deck) {
        hand.addCard(deck.draw())
    }

    fun cards(): List<Card> = hand.cards

    fun canDraw(): Boolean = !gameState.isFinal
}
