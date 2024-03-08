package model.participants

import model.card.Card
import model.result.Point
import model.result.Point.Companion.compareTo

abstract class Human(open val hand: Hand, open val humanName: HumanName) {
    fun getPointIncludingAce(): Point {
        return if (hand.hasAce()) {
            decideAceValue()
        } else {
            hand.point
        }
    }

    private fun decideAceValue(): Point {
        val point = hand.point
        return if (point <= Card.ACE_ADDITIONAL_POINT) {
            point + Card.ACE_ADDITIONAL_POINT
        } else {
            point
        }
    }

    abstract fun hit(): Boolean

    abstract fun canHit(): Boolean

    companion object {
        const val BUST_BOUND = 21
    }
}
