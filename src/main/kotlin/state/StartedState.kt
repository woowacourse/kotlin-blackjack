package state

import domain.card.Card
import domain.card.Cards

abstract class StartedState(protected val cards: Cards) : State {
    override val score: Int
        get() = cards.resultSum

    override fun getCards(): List<Card> {
        return cards.list.toList()
    }
}
