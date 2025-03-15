package blackjack.domain

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player

class GameResult(private val dealer: Dealer, players: List<Player>) {
    val playersGameResult: Map<Player, ResultStatus> = players.associateWith { it.setPlayerStatus(dealer) }

    fun updateGameResult() {
        playersGameResult.forEach { (_, result) ->
            when (result) {
                ResultStatus.BLACKJACK_WIN -> dealer.result.addLose()
                ResultStatus.PLAYER_WIN -> dealer.result.addLose()
                ResultStatus.PLAYER_LOSE -> dealer.result.addWin()
                ResultStatus.DRAW -> dealer.result.addDraw()
            }
        }
    }
}
