package domain.result

import domain.Dealer
import domain.Players

class PlayersResult(players: Players, dealer: Dealer) {
    val list: List<NameAndProfit>
    val sum: Int
        get() = list.sumOf { it.profitMoney }

    init {
        val nameAndProfits = mutableListOf<NameAndProfit>()
        players.list.forEach { player ->
            nameAndProfits.add(NameAndProfit(player.name, (dealer.getProfit(player) * player.betMoney).toInt()))
        }
        list = nameAndProfits
    }
}
