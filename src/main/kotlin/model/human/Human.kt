package model.human

import model.Hand

abstract class Human(val hand: Hand, open val humanInfo: HumanInfo) {
    fun getName(): String {
        return humanInfo.humanName.name
    }

    fun getMoneyAmount(): Int {
        return this.humanInfo.money.amount
    }

    fun isBlackJack(): Boolean = (hand.getSize() == DEFAULT_CARD_COUNT && hand.getPoint().isEqualTo(MAX_POINT))

    fun isBusted(): Boolean = !this.hand.isNotBusted()

    abstract fun isHittable(): Boolean

    companion object {
        private const val DEFAULT_CARD_COUNT = 2
        private const val MAX_POINT = 21
    }
}
