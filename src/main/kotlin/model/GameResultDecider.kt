package model

import kotlin.math.abs

data class PlayerResult(val name: String, val result: GameResult)

class GameResultDecider(private val dealer: Dealer, private val players: Players) {
    fun compareWinOrLose(): GameOutput {
        val playerResults: List<PlayerResult> =
            players.map { player ->
                PlayerResult(player.name, comparePlayerResult(player.getScore()))
            }

        val dealerWins = playerResults.count { it.result == GameResult.LOSE }
        val dealerLosses = playerResults.count { it.result == GameResult.WIN }
        return GameOutput(dealerWins, dealerLosses, playerResults)
    }

    private fun comparePlayerResult(playerScore: Int): GameResult =
        when {
            dealer.getScore() > BLACKJACK_SCORE -> GameResult.WIN
            playerScore > BLACKJACK_SCORE -> GameResult.LOSE
            else -> compareScores(playerScore)
        }

    private fun compareScores(playerScore: Int): GameResult {
        val dealerDiff = abs(BLACKJACK_SCORE - dealer.getScore())
        val playerDiff = abs(BLACKJACK_SCORE - playerScore)
        return when {
            playerDiff < dealerDiff -> GameResult.WIN
            playerDiff > dealerDiff -> GameResult.LOSE
            else -> GameResult.DRAW
        }
    }

    companion object {
        const val BLACKJACK_SCORE = 21
    }
}
