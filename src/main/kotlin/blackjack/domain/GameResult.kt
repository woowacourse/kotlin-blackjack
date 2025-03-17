package blackjack.domain

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player

class GameResult(private val dealer: Dealer, players: List<Player>) {
    val playersGameResult: Map<Player, PlayerResultStatus> = players.associateWith { it.getPlayerStatus(dealer) }
    val playerProfits = mutableMapOf<Player, Int>()

    var dealerRevenue: Int = 0
    var dealerWin: Int = 0
    var dealerLose: Int = 0
    var dealerDraw: Int = 0

    fun updateGameResult() {
        playersGameResult.forEach { (player, result) ->
            when (result) {
                PlayerResultStatus.BLACKJACK_WIN, PlayerResultStatus.PLAYER_WIN -> {
                    dealerLose++
                }

                PlayerResultStatus.PLAYER_LOSE -> {
                    dealerWin++
                    dealerRevenue + (-player.getBetAmount())
                }

                PlayerResultStatus.DRAW -> {
                    dealerDraw++
                }
            }
        }
    }

    fun calculatePlayerProfit(): Map<Player, Int> {
        playersGameResult.forEach { (player, result) ->
            val profit =
                when (result) {
                    PlayerResultStatus.BLACKJACK_WIN -> {
                        (player.getBetAmount() * 1.5).toInt()
                    }

                    PlayerResultStatus.PLAYER_WIN -> {
                        player.getBetAmount()
                    }

                    PlayerResultStatus.PLAYER_LOSE -> {
                        -player.getBetAmount()
                    }

                    PlayerResultStatus.DRAW -> {
                        0
                    }
                }
            playerProfits[player] = profit
        }

        return playerProfits
    }
}
