package blackjack.model.game

import Player
import blackjack.model.player.Dealer
import blackjack.model.player.PlayerResult

class GameResult(
    val dealer: Dealer,
    val playerResults: List<PlayerResult>,
) {
    val dealerProfit: Int
    val playerProfits: Map<Player, Int>

    init {
        playerProfits = calculatePlayerProfits()
        dealerProfit = calculateDealerProfit()
    }

    fun getPlayerWinCount(): Int = playerResults.count { it.result == Result.PLAYER_WIN }

    fun getDealerWinCount(): Int = playerResults.count { it.result == Result.DEALER_WIN }

    fun getDrawCount(): Int = playerResults.count { it.result == Result.DRAW }

    private fun calculatePlayerProfits(): Map<Player, Int> {
        return playerResults.associate { playerResult ->
            val profit = calculatePlayerProfit(playerResult)
            playerResult.player to profit
        }
    }

    private fun calculatePlayerProfit(playerResult: PlayerResult): Int {
        val player = playerResult.player
        val playerBlackjack = player.hand.isBlackjack()
        val dealerBlackjack = dealer.hand.isBlackjack()

        return when {
            playerBlackjack && !dealerBlackjack -> {
                (player.bettingMoney.bettingMoney * 1.5).toInt()
            }
            playerBlackjack && dealerBlackjack -> {
                0
            }
            else -> {
                adjustProfitBasedOnResult(playerResult)
            }
        }
    }

    private fun adjustProfitBasedOnResult(playerResult: PlayerResult): Int {
        val player = playerResult.player
        return when (playerResult.result) {
            Result.PLAYER_WIN -> player.bettingMoney.bettingMoney
            Result.DEALER_WIN -> -player.bettingMoney.bettingMoney
            Result.DRAW -> 0
        }
    }

    private fun calculateDealerProfit(): Int {
        return -playerProfits.values.sum()
    }
}
