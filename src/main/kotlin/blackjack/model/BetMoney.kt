package blackjack.model

@JvmInline
value class BetMoney(
    val amount: Long,
) {
    fun toBlackjackMoney(): BetMoney = BetMoney((amount * 1.5).toLong())

    fun toWinMoney(): BetMoney = BetMoney(amount)

    fun toDrawMoney(): BetMoney = BetMoney(0L)

    fun toLoseMoney(): BetMoney = BetMoney(amount * -1)
}
