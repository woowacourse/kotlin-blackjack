package entity

@JvmInline
value class Money(val value: Int) {
    operator fun times(other: Double): Money = Money((value * other).toInt())
    operator fun times(other: Int): Money = Money(value * other)
}
