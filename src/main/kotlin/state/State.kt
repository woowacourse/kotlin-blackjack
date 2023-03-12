package state

import domain.card.Card
import domain.card.Cards

interface State {
    fun draw(card: Card): State

    fun next(nextCards: Cards): State
}
