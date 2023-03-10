package blackjack.domain.state

import blackjack.domain.card.Cards

abstract class InTurn(final override val cards: Cards) : State {
    override val score: Score = cards.calculateScore()
    override val size = cards.size
}
