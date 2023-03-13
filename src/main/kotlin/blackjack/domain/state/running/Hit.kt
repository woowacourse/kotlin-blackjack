package blackjack.domain.state.running

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.state.State
import blackjack.domain.state.finished.Bust

class Hit(cards: Cards) : Running(cards) {
    override val toNextState: Boolean
        get() = cards.isBust()

    constructor(vararg cards: Card) : this(Cards(*cards))

    override fun next(): State = Bust(cards)
}
