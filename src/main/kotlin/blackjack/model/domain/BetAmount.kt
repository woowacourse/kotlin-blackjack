package blackjack.model.domain

@JvmInline
value class BetAmount(private val betAmount: Int) {
    val BlackjackMoney get() = betAmount * 1.5
    val LoseMoney get() = betAmount * -1
    val WinMoney get() = betAmount
}
