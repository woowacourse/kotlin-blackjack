package blackjack.model

data class Money(val money: Double) : Comparable<Money> {
    operator fun plus(other: Money) = Money(money + other.money)
    operator fun minus(other: Money) = Money(money - other.money)

    operator fun times(rate: Double) = Money(rate * money)
    operator fun unaryMinus() = Money(-money)
    override fun compareTo(other: Money): Int = money.compareTo(other.money)
}
