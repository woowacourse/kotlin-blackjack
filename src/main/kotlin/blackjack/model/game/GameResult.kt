package blackjack.model.game

import blackjack.model.user.Participant.Dealer
import blackjack.model.user.Participant.Player

class GameResult(val dealer: Dealer, val players: List<Player>) {
    private val playersResult: Map<Player, Result> = judgePlayersResult()
    val gameRevenue = GameRevenue(playersResult)

    private fun judgePlayersResult(): Map<Player, Result> {
        return players.associateWith { player ->
            Result.judgeResult(dealer, player)
        }
    }
}
