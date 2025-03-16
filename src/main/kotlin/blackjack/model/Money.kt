package blackjack.model

@JvmInline
value class Money private constructor(
    val amount: Long,
) {
    companion object {
        fun from(amount: Long): Money = Money(amount)

        fun toBlackjackMoney(money: Money): Money = Money((money.amount * 1.5).toLong())

        fun toWinMoney(money: Money): Money = Money(money.amount)

        fun toDrawMoney(): Money = Money(0L)

        fun toLoseMoney(money: Money): Money = Money(money.amount * -1)
    }
}
