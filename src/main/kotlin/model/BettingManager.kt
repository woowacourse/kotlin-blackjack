package model

class BettingManager {
    private val bets: MutableMap<Player, Int> = mutableMapOf()

    fun placeBet(
        player: Player,
        amount: Int,
    ) {
        bets[player] = amount
    }

    fun getProfit(player: Player): Int  {
        return bets[player] ?: 0
    }
}
