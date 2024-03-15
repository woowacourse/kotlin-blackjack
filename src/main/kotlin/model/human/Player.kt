package model.human

import model.Hand

class Player(hand: Hand, override val humanInfo: HumanInfo = HumanInfo("Player")) :
    Human(hand, humanInfo) {
    override fun isPossible(): Boolean = hand.getPoint().isLessThan(21)
}
