package model

@JvmInline
value class Money(private val amount: Int) {
    fun multiply(multiplier: Float): Money = Money((this.amount * multiplier).toInt())

    fun toInt(): Int = amount
}
