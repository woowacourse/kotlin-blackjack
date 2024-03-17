package model.human

import model.Money

class HumanInfo(val humanName: HumanName, var money: Money = Money(0)) {
    constructor(humanName: String) : this(HumanName(humanName), Money(0))

    fun changeMoney(amount: Int) {
        money.changeAmount(amount)
    }

    fun exchangeMoney(
        other: HumanInfo,
        rate: Double,
    ) {
        this.money.applyProfitRate(other.money, rate)
    }

    companion object {
        fun ofPrimitive(
            name: String,
            amount: Int,
        ): HumanInfo = HumanInfo(HumanName(name), Money(amount))
    }
}
