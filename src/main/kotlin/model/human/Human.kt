package model.human

import model.Hand
import model.Point
import model.card.ValueType

abstract class Human(open val hand: Hand, open val humanName: HumanName) {
    fun getPointIncludingAce(): Point {
        return if (hand.cards.any { it.valueType == ValueType.ACE }) {
            decideAceValue()
        } else {
            hand.getPoint()
        }
    }

    private fun decideAceValue(): Point {
        val point = hand.getPoint().amount
        return if (point <= 10) {
            Point(point + 10)
        } else {
            Point(point)
        }
    }

    fun hits(count: Int) {
        repeat(count) {
            hit()
        }
    }

    fun hit(): Boolean {
        if (isPossible()) {
            hand.draw()
            return isPossible()
        }
        return false
    }

    abstract fun isPossible(): Boolean
}
