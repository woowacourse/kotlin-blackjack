package blackjack.domain.gamer

data class Money(val money: Int) {
    operator fun plus(other: Money): Money = Money(money + other.money)
    operator fun times(other: Double): Money = Money((money * other).toInt())
}