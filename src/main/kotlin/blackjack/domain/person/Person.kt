package blackjack.domain.person

import blackjack.domain.Score
import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.state.PersonState

abstract class Person {
    protected var state: PersonState = PersonState.HIT
    val gameState: PersonState get() = state

    private val hand: Hand = Hand()

    fun isDrawable(): Boolean {
        return !state.isFinal
    }

    fun draw(deck: Deck) {
        hand.addCard(deck.draw())
        state = PersonState.from(this)
    }

    fun cards(): List<Card> {
        return hand.cards
    }

    fun score(): Score {
        return Score.create(hand.cards)
    }
}
