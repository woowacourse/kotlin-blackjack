package blackjack.domain.person

import blackjack.const.GameRule
import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.state.PersonState

abstract class Person(
    hand: Hand,
) {
    protected lateinit var gameState: PersonState
    protected val hand = hand.copy()

    abstract fun draw(deck: Deck)

    fun cards(): List<Card> = hand.cards

    fun canDraw(): Boolean = !gameState.isFinal

    protected fun getDrawAmount(state: PersonState): Int {
        if (gameState == state) {
            return GameRule.FIRST_TURN_DRAW_AMOUNT
        }
        return GameRule.HIT_DRAW_AMOUNT
    }
}
