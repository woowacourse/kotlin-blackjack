package model

import kotlin.math.abs

data class PlayerResult(val player: Player, val result: GameResult)

class GameResultDecider(private val dealer: Dealer, private val players: Players) {
    fun compareWinOrLose(): GameOutput {
        val playerResults: List<PlayerResult> =
            players.map { player ->
                PlayerResult(player, comparePlayerResult(player))
            }

        val dealerWins = playerResults.count { it.result == GameResult.LOSE }
        val dealerLosses = playerResults.count { it.result == GameResult.WIN }
        return GameOutput(dealerWins, dealerLosses, playerResults)
    }

    private fun comparePlayerResult(player: Player): GameResult =
        when {
            dealer.getTotalScore() > BLACKJACK_SCORE -> GameResult.WIN
            dealer.isBlackJack() && player.isBlackJack() -> GameResult.PUSH
            player.isBlackJack() -> GameResult.BLACKJACK
            player.getTotalScore() > BLACKJACK_SCORE -> GameResult.LOSE
            else -> compareScores(player.getTotalScore())
        }

    private fun compareScores(playerScore: Int): GameResult {
        val dealerDiff = abs(BLACKJACK_SCORE - dealer.getTotalScore())
        val playerDiff = abs(BLACKJACK_SCORE - playerScore)
        return when {
            playerDiff < dealerDiff -> GameResult.WIN
            playerDiff > dealerDiff -> GameResult.LOSE
            else -> GameResult.PUSH
        }
    }

    companion object {
        const val BLACKJACK_SCORE = 21
    }
}
