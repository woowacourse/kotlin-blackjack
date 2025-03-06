package model

import kotlin.math.abs

data class GameOutput(
    val dealerWins: Int,
    val dealerLosses: Int,
    val playerResults: List<Pair<String, String>>,
)

class GameResultDecider(private val dealerTotalScore: Int, private val players: List<Player>) {
    fun compareWinOrLose(): GameOutput {
        var dealerWins = 0
        var dealerLosses = 0

        val playerResults = mutableListOf<Pair<String, String>>()

        players.forEach { player ->
            val playerScore = ScoreCalculator(player.playerCards).totalCardScore()
            val result =
                when {
                    dealerTotalScore > 21 -> "승"
                    playerScore > 21 -> "패"
                    else -> {
                        val dealerDiff = abs(21 - dealerTotalScore)
                        val playerDiff = abs(21 - playerScore)
                        when {
                            playerDiff < dealerDiff -> "승"
                            playerDiff > dealerDiff -> "패"
                            else -> "무"
                        }
                    }
                }

            when (result) {
                "승" -> dealerLosses++
                "패" -> dealerWins++
            }
            playerResults.add(player.name to result)
        }

        return GameOutput(dealerWins, dealerLosses, playerResults)
    }
}
