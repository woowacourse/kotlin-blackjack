package model.human

import model.Hand

class Dealer(override val hand: Hand, override val humanName: HumanName = HumanName.fromInput("딜러")) : Human(hand, humanName) {
    fun play() {
        while (hit()) ;
    }

    override fun isPossible(): Boolean = getPointIncludingAce().amount <= 16
}
