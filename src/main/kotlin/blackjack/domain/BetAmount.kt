package blackjack.domain

class BetAmount(private var amount: Int) {
    fun getAmount(): Int = amount

    fun update(result: ResultStatus) {
        amount +=
            when (result) {
                ResultStatus.BLACKJACK_WIN -> (amount * 1.5).toInt()
                ResultStatus.PLAYER_WIN -> amount
                ResultStatus.PLAYER_LOSE -> -amount
                else -> 0
            }
    }
}
