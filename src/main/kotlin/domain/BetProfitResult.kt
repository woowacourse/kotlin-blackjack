package domain

class BetProfitResult(val players: Players, val dealer: Dealer) {
    val playersResult: Map<Player, Int>
        get() {
            val map = mutableMapOf<Player, Int>()
            players.list.forEach {
                map[it] = (it.getGameResult(dealer).profitRate * it.betMoney).toInt()
            }
            return map
        }

    val dealerResult: Int
        get() = playersResult.values.sum() * -1
}
