package model.human

import model.Hand

class Player(hand: Hand, override val humanInfo: HumanInfo = HumanInfo(DEFAULT_NAME)) :
    Human(hand, humanInfo) {
    override fun isHittable(): Boolean = hand.getPoint().isLessThan(HIT_CONDITION)

    companion object {
        private const val HIT_CONDITION = 21
        private const val DEFAULT_NAME = "Player"
    }
}
