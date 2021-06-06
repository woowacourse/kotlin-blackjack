package blackjack.domain.result

import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.Money
import blackjack.domain.gamer.Player
import blackjack.domain.gamer.Players

class ProfitResult(val playerProfits: Map<Player, Int>, val dealerProfit: Int) {
    companion object {
        fun of(players: Players, dealer: Dealer): ProfitResult {
            val playerProfits = players.players.associateWith { it.profit(dealer).money }
            val dealerProfit = players.players.map { it.profit(dealer) * -1.0 }.reduce(Money::plus).money
            return ProfitResult(playerProfits, dealerProfit)
        }
    }
}