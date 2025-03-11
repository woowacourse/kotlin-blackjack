package blackjack.domain.person

import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.state.PersonState

abstract class Person {
    protected var state: PersonState = PersonState.HIT
    val gameState: PersonState get() = state

    private val hand: Hand = Hand()

    val cards: List<Card> get() = hand.cards

    val canDraw: Boolean get() = !gameState.isFinal

    val score: Int get() = hand.score

    fun draw(deck: Deck) {
        hand.addCard(deck.draw())
        state = PersonState.from(this)
    }
}
