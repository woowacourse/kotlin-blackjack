package blackjack.domain

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player

class GameResult(private val dealer: Dealer, players: List<Player>) {
    val playersGameResult: Map<Player, PlayerResultStatus> = players.associateWith { it.getPlayerStatus(dealer) }

    fun updateGameResult() {
        playersGameResult.forEach { (player, result) ->
            when (result) {
                PlayerResultStatus.BLACKJACK_WIN -> dealer.dealerResult.addLose()
                PlayerResultStatus.PLAYER_WIN -> dealer.dealerResult.addLose()
                PlayerResultStatus.PLAYER_LOSE -> {
                    dealer.dealerResult.addWin()
                    dealer.dealerResult.updateRevenueWhenPlayerLose(-player.getBetAmount())
                }
                PlayerResultStatus.DRAW -> dealer.dealerResult.addDraw()
            }
        }
    }
}
