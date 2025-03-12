package blackjack.domain

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player

class GameResult(private val dealer: Dealer, players: List<Player>) {
    val playersGameResult: Map<Player, PlayerResultStatus> = players.associateWith { it.setPlayerStatus(dealer) }

    fun updateGameResult() {
        playersGameResult.forEach { (player, result) ->
            when (result) {
                PlayerResultStatus.PLAYER_WIN -> dealer.result.addLose()
                PlayerResultStatus.PLAYER_LOSE -> dealer.result.addWin()
                PlayerResultStatus.DRAW -> dealer.result.addDraw()
            }
        }
    }
}
