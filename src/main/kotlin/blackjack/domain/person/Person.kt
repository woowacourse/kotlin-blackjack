package blackjack.domain.person

import blackjack.domain.Score
import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.state.PersonState

abstract class Person {
    protected var state: PersonState = PersonState.HIT
    val gameState: PersonState get() = state

    private var _score: Score = Score(emptyList())
    val score: Int get() = _score.value

    private val hand: Hand = Hand()

    fun draw(deck: Deck) {
        hand.addCard(deck.draw())
        _score = Score(cards())
        state = PersonState.from(this)
    }

    fun cards(): List<Card> = hand.cards

    fun canDraw(): Boolean = !gameState.isFinal
}
