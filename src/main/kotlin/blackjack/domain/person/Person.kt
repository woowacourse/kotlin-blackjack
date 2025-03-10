package blackjack.domain.person

import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.state.PersonState

abstract class Person(
    hand: Hand,
) {
    protected var state: PersonState = PersonState.HIT
    val gameState: PersonState get() = state

    protected val hand = hand.copy()

    fun draw(deck: Deck) {
        hand.addCard(deck.draw())
        state = PersonState.from(this)
    }

    fun cards(): List<Card> = hand.cards

    fun canDraw(): Boolean = !gameState.isFinal
}
