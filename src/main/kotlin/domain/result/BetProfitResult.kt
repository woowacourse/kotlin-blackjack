package domain.result

import domain.Dealer
import domain.Players

class BetProfitResult(private val players: Players, private val dealer: Dealer) {
    val playersResult: PlayersResult
        get() {
            val nameAndProfits = mutableListOf<NameAndProfit>()
            players.list.forEach {
                nameAndProfits.add(NameAndProfit(it.name, it.getGameProfitMoney(dealer)))
            }
            return PlayersResult(nameAndProfits)
        }

    val dealerResult: Int
        get() = playersResult.sum * -1
}
