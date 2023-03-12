package state

import domain.card.Card

class BlackJackState(val cards: List<Card>) : State {
    override fun next(card: Card): State {
        return this
    }
}
