package blackjack.model

data class Money(private val money: Double) {
    operator fun plus(other: Money) = Money(money + other.money)
    operator fun minus(other: Money) = Money(money - other.money)

    operator fun times(rate: Double) = Money(rate * money)
    operator fun unaryMinus() = Money(-money)
}
