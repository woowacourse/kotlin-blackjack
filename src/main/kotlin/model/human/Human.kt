package model.human

import model.Hand
import model.Point

abstract class Human(open val hand: Hand, open val humanName: HumanName) {
    fun getPointIncludingAce(): Point {
        return if (hand.hasAce()) {
            decideAceValue()
        } else {
            hand.getPoint()
        }
    }

    private fun decideAceValue(): Point {
        val point = hand.getPoint().amount
        return if (point <= ACE_POINT) {
            Point(point + ACE_POINT)
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
    fun isBlackJack(): Boolean  =
        (this.hand.cards.size == 2 && this.hand.isBlackjackPoint())

    fun isBusted(): Boolean = !this.hand.isNotBusted()
    abstract fun isPossible(): Boolean

    companion object {
        private const val ACE_POINT = 10
    }
}
