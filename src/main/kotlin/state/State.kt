package state

import domain.card.Card

interface State {
    fun next(card: Card): State
}
