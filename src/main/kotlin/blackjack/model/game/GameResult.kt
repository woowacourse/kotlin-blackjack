package blackjack.model.game

import Player
import blackjack.model.player.Dealer
import blackjack.model.player.PlayerResult

class GameResult(
    val dealer: Dealer,
    val playerResults: List<PlayerResult>,
) {
    fun getResultForPlayer(player: Player): Result? {
        return playerResults.find { it.player == player }?.result
    }
}
