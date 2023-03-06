package model

import model.Cards.Companion.DEALER_STANDARD_HIT_POINT

class Dealer(cards: Cards, name: Name = Name(DEALER)) : Participant(cards, name) {
    override fun isHit(): Boolean = cards.sum() <= DEALER_STANDARD_HIT_POINT

    companion object {
        const val DEALER = "딜러"
    }
}
