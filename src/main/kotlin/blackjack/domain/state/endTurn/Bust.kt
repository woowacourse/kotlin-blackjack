package blackjack.domain.state.endTurn

import blackjack.domain.card.Cards
import blackjack.domain.result.Outcome
import blackjack.domain.state.State

class Bust(cards: Cards) : EndTurn(cards) {
    override fun matchWith(otherState: State): Outcome =
        when (otherState) {
            is Bust -> Outcome.DRAW
            else -> Outcome.LOSE
        }
}
