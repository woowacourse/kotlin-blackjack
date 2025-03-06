package blackjack.domain.person

import blackjack.const.GameRule
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.state.PersonState

abstract class Person {
    protected val hand: Hand = Hand()

    private lateinit var _gameState: PersonState
    val gameState: PersonState get() = _gameState

    fun cards(): List<Card> = hand.cards

    fun canDraw(): Boolean {
        return !gameState.isFinal
    }

    fun score(): Int {
        val values = hand.cards.map { if (it.number == CardNumber.ACE) GameRule.ACE_OTHER_SCORE else it.number.value }
        val sum = values.sum()
        return values.fold(sum) { acc, number ->
            if (acc > GameRule.BLACKJACK_SCORE && number == GameRule.ACE_OTHER_SCORE) acc - GameRule.ACE_BASE_SCORE else acc
        }
    }

    abstract fun updateGameState()

    protected fun setGameState(state: PersonState) {
        _gameState = state
    }
}
