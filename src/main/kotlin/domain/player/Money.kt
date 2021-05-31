package domain.player

data class Money(val value: Int) {

    fun earn(money: Money): Money {
        return Money(this.value + money.value)
    }

    fun lose(money: Money): Money {
        return Money(this.value - money.value)
    }

    fun asBlackJackPrize(): Money {
        return Money((this.value * 1.5).toInt())
    }

    companion object {
        val ZERO = Money(0)
    }
}