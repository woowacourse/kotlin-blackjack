package model

import kotlin.math.abs

data class PlayerResult(val name: String, val result: VictoryStatus)

class GameResultDecider(private val dealer: Dealer, private val players: Players) {
    fun compareWinOrLose(): GameOutput {
        val playerResults: List<PlayerResult> =
            players.map { player ->
                PlayerResult(player.name, comparePlayerResult(player.currentScore))
            }

        val dealerWins = playerResults.count { it.result == VictoryStatus.LOSE }
        val dealerLosses = playerResults.count { it.result == VictoryStatus.WIN }
        return GameOutput(dealerWins, dealerLosses, playerResults)
    }

    private fun comparePlayerResult(playerScore: Int): VictoryStatus =
        when {
            dealer.currentScore > BLACKJACK_SCORE -> VictoryStatus.WIN
            playerScore > BLACKJACK_SCORE -> VictoryStatus.LOSE
            else -> compareScores(playerScore)
        }

    private fun compareScores(playerScore: Int): VictoryStatus {
        val dealerDiff = abs(BLACKJACK_SCORE - dealer.currentScore)
        val playerDiff = abs(BLACKJACK_SCORE - playerScore)
        return when {
            playerDiff < dealerDiff -> VictoryStatus.WIN
            playerDiff > dealerDiff -> VictoryStatus.LOSE
            else -> VictoryStatus.DRAW
        }
    }

    companion object {
        const val BLACKJACK_SCORE = 21
    }
}
