package model.result

import model.participant.Dealer
import model.participant.Player
import model.participant.Players
import kotlin.math.abs

class ProfitCalculator(private val dealer: Dealer, private val players: Players) {
    val playerProfits: List<PlayerProfit> =
        players.map { player -> PlayerProfit(player.name, comparePlayerResult(player)) }

    fun dealerProfit(playerProfits: List<PlayerProfit>): Float {
        var initialDealerProfit = players.map { it.betAmount }.sum()

        playerProfits.forEachIndexed { index, playerResult ->
            if (playerResult.profit >= ZERO) initialDealerProfit -= players[index].betAmount
        }

        return initialDealerProfit
    }

    private fun comparePlayerResult(player: Player): Float =
        when {
            player.isBackJack && !dealer.isBackJack -> player.betAmount * ONE_AND_HALF
            player.isBackJack && dealer.isBackJack -> ZERO
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
            else -> ZERO
        }
    }

    companion object {
        const val BLACKJACK_SCORE = 21
        private const val ZERO = 0f
        private const val ONE_AND_HALF = 1.5f
    }
}
