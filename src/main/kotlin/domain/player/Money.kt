package domain.player

const val BLACK_JACK_PRIZE_MULTIPLES = 1.5

data class Money(val value: Int) {

    fun earn(money: Money) = Money(this.value + money.value)

    fun lose(money: Money) = Money(this.value - money.value)

    fun asBlackJackPrize() = Money((this.value * BLACK_JACK_PRIZE_MULTIPLES).toInt())

    companion object {
        val ZERO = Money(0)
    }
}
