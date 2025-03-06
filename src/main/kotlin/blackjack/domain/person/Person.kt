package blackjack.domain.person

import blackjack.domain.ScoreCalculator
import blackjack.domain.card.Card
import blackjack.domain.state.PersonState

abstract class Person(private val scoreCalculator: ScoreCalculator = ScoreCalculator()) {
    protected val hand: Hand = Hand()

    private lateinit var _gameState: PersonState
    val gameState: PersonState get() = _gameState

    fun cards(): List<Card> = hand.cards

    fun canDraw(): Boolean = !gameState.isFinal

    fun score(): Int = scoreCalculator.calculate(cards())

    abstract fun updateGameState()

    protected fun setGameState(state: PersonState) {
        _gameState = state
    }
}
