package model.human

import model.Money

class HumanInfo(val humanName: HumanName, val money: Money = Money(DEFAULT_MONEY_AMOUNT)) {
    constructor(humanName: String) : this(HumanName(humanName), Money(DEFAULT_MONEY_AMOUNT))

    fun changeMoney(amount: Int) {
        money.changeAmount(amount)
    }

    fun applyResultToMoney(
        other: HumanInfo,
        rate: Double,
    ) {
        this.money.applyProfitRate(other.money, rate)
    }

    companion object {
        private const val DEFAULT_MONEY_AMOUNT = 0
    }
}
