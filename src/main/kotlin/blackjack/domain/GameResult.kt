package blackjack.domain

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.domain.participant.PlayerResultStatus

class GameResult(private val dealer: Dealer, players: List<Player>) {
    private val playersGameResult: Map<Player, PlayerResultStatus> =
        players.associateWith { it.getPlayerStatus(dealer) }
    private val playerProfits: Map<Player, Int> = calculatePlayerProfits()

    val dealerRevenue: Int = -playerProfits.values.sum()
    val dealerWin: Int = playersGameResult.count { it.value == PlayerResultStatus.PLAYER_LOSE }
    val dealerLose: Int =
        playersGameResult.count { it.value in listOf(PlayerResultStatus.BLACKJACK_WIN, PlayerResultStatus.PLAYER_WIN) }
    val dealerDraw: Int = playersGameResult.count { it.value == PlayerResultStatus.DRAW }

    fun getPlayerResults(): Map<Player, PlayerResultStatus> = playersGameResult

    fun getPlayerProfits(): Map<Player, Int> = playerProfits

    private fun calculatePlayerProfits(): Map<Player, Int> {
        return playersGameResult.mapValues { (player, result) ->
            when (result) {
                PlayerResultStatus.BLACKJACK_WIN -> (player.getBetAmount() * 1.5).toInt()
                PlayerResultStatus.PLAYER_WIN -> player.getBetAmount()
                PlayerResultStatus.PLAYER_LOSE -> -player.getBetAmount()
                PlayerResultStatus.DRAW -> 0
            }
        }
    }
}
