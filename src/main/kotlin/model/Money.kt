package model

@JvmInline
value class Money(val amount: Int) {
    fun multiply(multiplier: Float): Money = Money((this.amount * multiplier).toInt())
}
