package blackjack.model

@JvmInline
value class Money(
    val amount: Long,
) {
    fun toBlackjackMoney(): Money = Money((amount * 1.5).toLong())

    fun toWinMoney(): Money = Money(amount)

    fun toDrawMoney(): Money = Money(0L)

    fun toLoseMoney(): Money = Money(amount * -1)
}
