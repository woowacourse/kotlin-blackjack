package domain.result

import domain.Dealer
import domain.Players

class BetProfitResult(players: Players, dealer: Dealer) {
    val playersResult: PlayersResult

    val dealerResult: Int
        get() = playersResult.sum * -1

    init {
        val nameAndProfits = mutableListOf<NameAndProfit>()
        players.list.forEach {
            nameAndProfits.add(NameAndProfit(it.name, it.getGameProfitMoney(dealer)))
        }
        playersResult = PlayersResult(nameAndProfits)
    }
}
