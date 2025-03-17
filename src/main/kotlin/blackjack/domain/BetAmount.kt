package blackjack.domain

class BetAmount(private var amount: Int) {
    init {
        require(amount > 0) { "배팅 금액은 0원 이상 입력해주세요." }
    }

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
