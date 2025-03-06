package blackjack.domain.person

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
}
