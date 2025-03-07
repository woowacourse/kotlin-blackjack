package model

import kotlin.math.abs

data class PlayerResult(val name: String, val result: String)

class GameResultDecider(private val dealer: Dealer, private val players: List<Player>) {
    fun compareWinOrLose(): GameOutput {
        val playerResults: List<PlayerResult> = players.map { player ->
            PlayerResult(player.name, comparePlayerResult(player.getScore()))
        }

        val dealerWins = playerResults.count { it.result == LOSE }
        val dealerLosses = playerResults.count { it.result == WIN }
        return GameOutput(dealerWins, dealerLosses, playerResults)
    }

    private fun comparePlayerResult(playerScore: Int): String = when {
        dealer.getScore() > STANDARD_SCORE -> WIN
        playerScore > STANDARD_SCORE -> LOSE
        else -> compareScores(playerScore)
    }

    private fun compareScores(playerScore: Int): String {
        val dealerDiff = abs(STANDARD_SCORE - dealer.getScore())
        val playerDiff = abs(STANDARD_SCORE - playerScore)
        return when {
            playerDiff < dealerDiff -> WIN
            playerDiff > dealerDiff -> LOSE
            else -> DRAW
        }
    }

    companion object {
        private const val STANDARD_SCORE = 21
        private const val WIN = "승"
        private const val LOSE = "패"
        private const val DRAW = "무"
    }
}
