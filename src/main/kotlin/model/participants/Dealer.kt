package model.participants

import model.cards.Hand

class Dealer(hand: Hand, name: Name = Name(DEALER)) : Participant(hand, name) {
    override fun isHit(): Boolean = hand.sum() < HIT_STANDARD_POINT

    companion object {
        const val DEALER = "딜러"
        private const val HIT_STANDARD_POINT = 17
    }
}
