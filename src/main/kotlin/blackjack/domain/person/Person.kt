package blackjack.domain.person

import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.state.PersonState

abstract class Person {
    protected var state: PersonState = PersonState.HIT
    val gameState: PersonState get() = state

    private val hand: Hand = Hand()

    fun draw(deck: Deck) {
        hand.addCard(deck.draw())
        state = PersonState.from(this)
    }

    fun score(): Int = hand.score

    fun cards(): List<Card> = hand.cards

    fun canDraw(): Boolean = !gameState.isFinal
}
