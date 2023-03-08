package domain

class BetProfitResult private constructor(val players: Players, val dealer: Dealer) {
    private val result: Map<Player, GameResult>
        get() = players.result(dealer)

    val playersResult: Map<Player, Int>
        get() {
            val map = mutableMapOf<Player, Int>()
            result.keys.forEach {
                map[it] = ((result[it]?.profitRate ?: 0.0) * it.betMoney).toInt()
            }
            return map
        }

    val dealerResult: Int
        get() = playersResult.values.sum() * -1

    companion object {
        fun of(players: Players, dealer: Dealer): BetProfitResult {
            return BetProfitResult(players, dealer)
        }
    }
}
