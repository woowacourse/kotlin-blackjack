package blackjack.domain.person

import blackjack.const.GameRule
import blackjack.domain.ScoreCalculator
import blackjack.domain.card.Card
import blackjack.domain.state.PersonState

abstract class Person(
    protected val hand: Hand,
    private val calculator: ScoreCalculator = ScoreCalculator(),
) {
    protected lateinit var gameState: PersonState

    fun cards(): List<Card> = hand.cards

    fun canDraw(): Boolean = !gameState.isFinal

    fun score(): Int = calculator.calculate(cards())

    protected fun getDrawAmount(state: PersonState): Int {
        if (gameState == state) {
            return GameRule.FIRST_TURN_DRAW_AMOUNT
        }
        return GameRule.HIT_DRAW_AMOUNT
    }
}
