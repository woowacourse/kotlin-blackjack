package model.tools

data class Money constructor(var amount: Int = 0) {

    fun bet(bettingMoney: Money) {
        amount += bettingMoney.amount
    }

    fun lose(userMoney: Int) {
        amount = -userMoney
    }

    fun earn(magnification: Double) {
        amount = (amount * magnification).toInt()
    }

    companion object {
        fun from(money: Int): Money {
            return Money(money)
        }
    }
}
