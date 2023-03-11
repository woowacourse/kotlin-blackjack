package blackjack.domain.state.endTurn

import blackjack.domain.card.Cards
import blackjack.domain.state.Outcome
import blackjack.domain.state.Score
import blackjack.domain.state.State
import blackjack.domain.state.inTurn.Hit

class Stay(cards: Cards) : EndTurn(cards) {
    override fun matchWith(otherState: State): Outcome =
        when (otherState) {
            is BlackJack -> Outcome.DRAW
            is Bust -> Outcome.WIN
            is Stay -> compareScore(otherState.score)
            is Hit -> compareScore(otherState.score)
            else -> {
                throw IllegalStateException("Dealer's state is not valid") }
        }

    private fun compareScore(otherScore: Score) = when {
        otherScore > score -> Outcome.LOSE
        otherScore < score -> Outcome.WIN
        else -> Outcome.DRAW
    }
}
