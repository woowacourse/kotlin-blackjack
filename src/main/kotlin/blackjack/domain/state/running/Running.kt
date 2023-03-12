package blackjack.domain.state.running

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.state.State

abstract class Running(val cards: Cards) : State {
    abstract val toNextState: Boolean

    override fun draw(card: Card): State {
        cards.add(card)

        if (toNextState) return next()
        return this
    }

    abstract fun next(): State
}
