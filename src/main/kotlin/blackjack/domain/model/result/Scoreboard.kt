package blackjack.domain.model.result

import blackjack.domain.model.Money
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Player

class Scoreboard(val dealer: Dealer, val players: List<Player>) {
    fun playersProfits(): Map<Player, Money> =
        players.associateWith { player ->
            player.compareAgainst(dealer).cashOut(player)
        }

    fun dealerProfit(players: List<Player>): Money {
        return Money(players.sumOf { player -> dealer.compareAgainst(player).cashOut(player).value })
    }
}
