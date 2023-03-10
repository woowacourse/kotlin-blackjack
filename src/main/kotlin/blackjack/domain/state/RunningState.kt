package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

abstract class RunningState(private val cards: Cards) : CardState {
    override fun stay(): CardState = StayState(cards)

    override fun draw(card: Card): CardState {
        cards.add(card)
        if (nextStateCondition()) {
            return nextState()
        }
        return this
    }

    abstract fun nextStateCondition(): Boolean

    abstract fun nextState(): CardState
}
