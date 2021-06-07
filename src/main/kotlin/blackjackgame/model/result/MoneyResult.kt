package blackjackgame.model.result

import blackjackgame.model.player.Dealer
import blackjackgame.model.player.Player

class MoneyResult(private val dealer: Dealer, private val players: List<Player>) {
    private val results = mutableListOf<Pair<String, Int>>()

    fun getMoneyResult(): List<Pair<String, Int>> {
        results.add(dealer.name to dealer.finalMoney)
        players.map {
            results.add(it.name to it.finalMoney)
        }
        return results.toList()
    }
}