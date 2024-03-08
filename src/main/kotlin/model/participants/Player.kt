package model.participants

import model.result.Point.Companion.compareTo

class Player(override val hand: Hand, override val humanName: HumanName = HumanName.fromInput("Player")) : Human(hand, humanName) {
    override fun hit(): Boolean {
        if (canHit()) {
            hand.draw()
            return canHit()
        }
        return false
    }

    override fun canHit(): Boolean = getPointIncludingAce() < BUST_BOUND
}
