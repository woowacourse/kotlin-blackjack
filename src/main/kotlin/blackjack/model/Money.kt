package blackjack.model

@JvmInline
value class Money(
    val value: Int,
) {
    operator fun times(floatNumber: Float): Money = Money((this.value * floatNumber).toInt())

    operator fun times(intNumber: Int): Money = Money(this.value * intNumber)
}
