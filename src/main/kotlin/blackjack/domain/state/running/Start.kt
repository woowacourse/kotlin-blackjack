package blackjack.domain.state.running

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.state.State
import blackjack.domain.state.finished.Blackjack

class Start(cards: Cards) : Running(cards) {
    override val toNextState: Boolean
        get() = cards.isStartLimitSize()

    constructor(vararg cards: Card) : this(Cards(*cards))

    override fun next(): State {
        if (cards.isBlackjack()) return Blackjack(cards)
        return Hit(cards)
    }
}
