package blackjack.domain.state.endTurn

import blackjack.domain.card.Cards
import blackjack.domain.state.Outcome
import blackjack.domain.state.Outcome.DRAW
import blackjack.domain.state.Outcome.LOSE
import blackjack.domain.state.Outcome.WIN
import blackjack.domain.state.Score
import blackjack.domain.state.State

class Stay(cards: Cards) : EndTurn(cards) {
    init {
        cards.calculateScore().let {
            require(it.isBlackJack || it.isBust.not()) { ERROR_BUST_SCORE }
        }
    }

    override fun matchWith(otherState: State): Outcome {
        return when (otherState) {
            is Bust -> WIN
            is BlackJack -> compareBlackJack()
            is Stay -> compareScore(otherState.score)
            else -> throw IllegalStateException(ERROR_INVALID_STATE)
        }
    }

    private fun compareBlackJack(): Outcome = when {
        score.isBlackJack -> DRAW
        else -> LOSE
    }

    private fun compareScore(otherScore: Score): Outcome = when {
        otherScore > score -> LOSE
        otherScore < score -> WIN
        else -> DRAW
    }

    companion object {
        private const val ERROR_BUST_SCORE = "Stay는 21점 이하이어야 합니다."
        private const val ERROR_INVALID_STATE = "비교할 수 없는 상태입니다."
    }
}
