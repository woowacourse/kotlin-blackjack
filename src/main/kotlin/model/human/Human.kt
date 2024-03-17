package model.human

import model.Hand

abstract class Human(val hand: Hand, open val humanInfo: HumanInfo) {
    fun getName(): String {
        return humanInfo.humanName.name
    }

    fun getMoneyAmount(): Int {
        return this.humanInfo.money.amount
    }

    fun isBlackJack(): Boolean = (this.hand.cards.size == 2 && this.hand.getPoint().isEqualTo(21))

    fun isBusted(): Boolean = !this.hand.isNotBusted()

    abstract fun isPossible(): Boolean
}
