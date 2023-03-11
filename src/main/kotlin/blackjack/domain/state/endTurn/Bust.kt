package blackjack.domain.state.endTurn

import blackjack.domain.card.Cards
import blackjack.domain.state.Outcome
import blackjack.domain.state.Outcome.DRAW
import blackjack.domain.state.Outcome.LOSE
import blackjack.domain.state.State

class Bust(cards: Cards) : EndTurn(cards) {
    init {
        require(cards.calculateScore().isBust) { ERROR_BUST_SCORE }
    }
    override fun matchWith(otherState: State): Outcome =
        when (otherState) {
            is Bust -> DRAW
            else -> LOSE
        }

    companion object {
        private const val ERROR_BUST_SCORE = "Bust는 22점 이상이어야 합니다."
    }
}
