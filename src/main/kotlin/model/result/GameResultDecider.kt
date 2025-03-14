package model.result

import model.participant.Dealer
import model.participant.Player
import model.participant.Players
import kotlin.math.abs

class GameResultDecider(private val dealer: Dealer, private val players: Players) {
    fun totalGameResult(): GameResult {
        val playerResults: List<PlayerResult> =
            players.map { player ->
                PlayerResult(player.name, comparePlayerResult(player))
            }

        return GameResult(dealerResult(playerResults), playerResults)
    }

    private fun comparePlayerResult(player: Player): Float =
        when {
            player.isBackJack && !dealer.isBackJack -> player.betAmount * 1.5f
            player.isBackJack && dealer.isBackJack -> 0f
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

    private fun dealerResult(playerResults: List<PlayerResult>): Float {
        var initialDealerProfit = players.map { it.betAmount }.sum()

        playerResults.forEachIndexed { index, playerResult ->
            if (playerResult.profit >= 0) initialDealerProfit -= players[index].betAmount
        }
        return initialDealerProfit
    }

    companion object {
        const val BLACKJACK_SCORE = 21
    }
}
