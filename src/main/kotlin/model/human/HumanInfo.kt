package model.human

import model.Money

class HumanInfo(val humanName: HumanName, val money: Money = Money(DEFAULT_MONEY_AMOUNT)) {
    constructor(humanName: String) : this(HumanName(humanName), Money(DEFAULT_MONEY_AMOUNT))

    fun setMoney(amount: Int) {
        money.changeAmount(amount)
    }

    fun getName(): String = humanName.name

    fun getMoneyAmount(): Int = money.amount

    fun add(other: Money) {
        money.add(other)
    }

    fun applyResultToDealerMoney(
        other: Int,
        rate: Double,
    ) {
        money.add(other * rate.toInt())
    }

    companion object {
        private const val DEFAULT_MONEY_AMOUNT = 0
    }
}
