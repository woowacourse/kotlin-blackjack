package model.human

import model.Hand

class Player(override val hand: Hand, override val humanName: HumanName = HumanName.fromInput("Player")) : Human(hand, humanName) {
    override fun isPossible(): Boolean = getPointIncludingAce().amount < 21
}
