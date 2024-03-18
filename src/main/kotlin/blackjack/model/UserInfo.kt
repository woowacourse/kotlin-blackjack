package blackjack.model

class UserInfo(val nickname: Nickname) {
    private var _betAmount: BetAmount = BetAmount(DEFAULT_BETTING_AMOUNT)
    val betAmount: BetAmount
        get() = _betAmount

    fun plusBet(amount: BetAmount) {
        _betAmount += amount
    }

    companion object {
        const val DEFAULT_BETTING_AMOUNT = 0
    }
}
