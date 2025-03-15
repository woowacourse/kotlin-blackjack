package blackjack.domain.model.result

import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Player

class Scoreboard(val dealer: Dealer, private val players: List<Player>) {
    fun playersProfits(): Map<Player, Int> =
        players.associateWith { player ->
            player.compareAgainst(dealer).cashOut(player)
        }

    fun dealerProfit(playersProfits: Map<Player, Int>): Int = -playersProfits.values.sum()
}
