package model

import kotlin.math.abs

class GameResultDecider(private val dealer: Dealer, private val players: Players) {
    fun compareWinOrLose(): GameOutput {
        val playerResults: List<PlayerResult> =
            players.map { player ->
                PlayerResult(player.name, comparePlayerResult(player))
            }

        return GameOutput(playerResults)
    }

    private fun comparePlayerResult(player: Player): Float =
        when {
            player.isBackJack && !dealer.isBackJack -> player.betAmount * 1.5f
            player.isBackJack && dealer.isBackJack -> player.betAmount
            dealer.currentScore > BLACKJACK_SCORE -> player.betAmount
            player.currentScore > BLACKJACK_SCORE -> -player.betAmount
            else -> compareScores(player)
        }

    private fun compareScores(player: Player): Float {
        val dealerDiff = abs(BLACKJACK_SCORE - dealer.currentScore)
        val playerDiff = abs(BLACKJACK_SCORE - player.currentScore)
        return when {
            playerDiff < dealerDiff -> player.betAmount
            playerDiff > dealerDiff -> -player.betAmount
            else -> 0f
        }
    }

    companion object {
        const val BLACKJACK_SCORE = 21
    }
}
