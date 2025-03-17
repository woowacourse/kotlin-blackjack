package blackjack.domain

class BetAmount(private var amount: Int) {
    fun getAmount(): Int = amount

    fun update(result: PlayerResultStatus) {
        amount =
            when (result) {
                PlayerResultStatus.BLACKJACK_WIN -> (amount * 1.5).toInt()
                PlayerResultStatus.PLAYER_WIN -> amount
                PlayerResultStatus.PLAYER_LOSE -> -amount
                PlayerResultStatus.DRAW -> 0
            }
    }
}
