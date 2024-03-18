package blackjack.model.game

import blackjack.model.player.Dealer
import blackjack.model.player.PlayerResult

class GameResult(val dealer: Dealer, val playerResults: List<PlayerResult>) {
    val dealerProfit: Int
        get() = -playerResults.sumOf { it.profit }

    fun getPlayerWinCount(): Int = playerResults.count { it.result == Result.PLAYER_WIN }

    fun getDealerWinCount(): Int = playerResults.count { it.result == Result.DEALER_WIN }

    fun getDrawCount(): Int = playerResults.count { it.result == Result.DRAW }
}
