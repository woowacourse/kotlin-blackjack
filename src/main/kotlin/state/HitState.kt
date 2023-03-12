package state

import domain.card.Card

class HitState(val cards: List<Card>) : State {
    override fun next(card: Card): State {
        return this
    }
}
