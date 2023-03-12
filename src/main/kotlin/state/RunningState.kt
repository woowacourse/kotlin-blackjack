package state

import domain.card.Card
import domain.card.Cards

abstract class RunningState(val cards: Cards) : State {
    override fun draw(card: Card): State {
        val nextCards = cards.add2(card)
        return next(nextCards)
    }
}
