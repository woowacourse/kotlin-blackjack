package blackjack.domain.state.endTurn

import blackjack.domain.card.Cards
import blackjack.domain.state.Outcome
import blackjack.domain.state.Score
import blackjack.domain.state.State

class Stay(cards: Cards) : EndTurn(cards) {
    override fun matchWith(otherState: State): Outcome {
        return when (otherState) {
            is Bust -> Outcome.WIN
            is BlackJack -> compareBlackJack()
            is Stay -> compareScore(otherState.score)
            else -> throw IllegalStateException("Dealer's state is not valid")
        }
    }

    private fun compareBlackJack() = when {
        score.isBlackJack -> Outcome.DRAW
        else -> Outcome.LOSE
    }

    private fun compareScore(otherScore: Score) = when {
        otherScore > score -> Outcome.LOSE
        otherScore < score -> Outcome.WIN
        else -> Outcome.DRAW
    }
}
