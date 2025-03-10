package blackjack.domain.participants

import blackjack.const.GameRule
import blackjack.domain.ScoreCalculator
import blackjack.domain.card.Card
import blackjack.domain.state.GameState

abstract class Participant(
    private val _hand: MutableList<Card> = mutableListOf(),
    private val calculator: ScoreCalculator = ScoreCalculator(),
) {
    val hand: List<Card>
        get() = _hand.toList()

    var gameState: GameState = GameState.FIRST_TURN
        private set

    abstract fun shouldHit(): Boolean

    fun addCard(card: Card) {
        _hand.add(card)
        updateState()
    }

    fun score(): Int = calculator.calculate(hand)

    fun getDrawAmount(): Int {
        if (gameState == GameState.FIRST_TURN) {
            return GameRule.FIRST_TURN_DRAW_AMOUNT
        }

        if (gameState == GameState.HIT) {
            return GameRule.HIT_DRAW_AMOUNT
        }

        throw IllegalArgumentException("[ERROR] 카드를 뽑을 수 없습니다.")
    }

    private fun updateState() {
        gameState = GameState.from(this)
    }
}
