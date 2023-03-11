package blackjack.domain.state.inTurn

import blackjack.domain.card.Cards
import blackjack.domain.state.Score
import blackjack.domain.state.State

abstract class InTurn(final override val cards: Cards) : State {
    override val score: Score = cards.calculateScore()
    override val size = cards.size
}
