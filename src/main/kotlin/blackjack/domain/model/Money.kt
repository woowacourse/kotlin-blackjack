package blackjack.domain.model

@JvmInline
value class Money(val value: Int) {
    operator fun compareTo(other: Money): Int = this.value.compareTo(other.value)

    operator fun times(factor: Double): Money = Money((value * factor).toInt())
}
