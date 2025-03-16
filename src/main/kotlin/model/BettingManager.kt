package model

class BettingManager {
    private val bets: MutableMap<Player, Money> = mutableMapOf()

    fun placeBet(
        player: Player,
        amount: Int,
    ) {
        require(amount > 0) { INITIAL_BETTING_AMOUNT_MESSAGE }
        bets[player] = Money(amount)
    }

    fun getProfit(player: Player): Money {
        return bets[player] ?: Money(0)
    }

    companion object {
        private const val INITIAL_BETTING_AMOUNT_MESSAGE = "베팅 금액은 0보다 커야합니다."
    }
}
