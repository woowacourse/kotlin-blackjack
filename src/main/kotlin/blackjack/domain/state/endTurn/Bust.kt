package blackjack.domain.state.endTurn

import blackjack.domain.card.Cards
import blackjack.domain.state.Outcome
import blackjack.domain.state.Outcome.DRAW
import blackjack.domain.state.Outcome.LOSE
import blackjack.domain.state.State

class Bust(cards: Cards) : EndTurn(cards) {
    override fun matchWith(otherState: State): Outcome =
        when (otherState) {
            is Bust -> DRAW
            else -> LOSE
        }
}
