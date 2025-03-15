package model.result

import model.participant.Dealer
import model.participant.Player
import model.participant.Players
import kotlin.math.abs

class ProfitCalculator(private val dealer: Dealer, private val players: Players) {
    val playerProfits: List<PlayerProfit> =
        players.map { player -> PlayerProfit(player.name, playerProfit(player)) }

    fun dealerProfit(): Float {
        var initialDealerProfit = players.map { it.betAmount }.sum()

        playerProfits.forEachIndexed { index, playerProfit ->
            if (playerProfit.money >= ZERO) initialDealerProfit -= players[index].betAmount
        }

        return initialDealerProfit
    }

    private fun playerProfit(player: Player): Float =
        when {
            player.isBlackJack && !dealer.isBlackJack -> player.betAmount * ONE_AND_HALF
            player.isBlackJack && dealer.isBlackJack -> ZERO
            dealer.isBust -> player.betAmount
            player.isBust -> -player.betAmount
            else -> compareScores(player)
        }

    private fun compareScores(player: Player): Float {
        val dealerDiff = abs(BLACKJACK_SCORE - dealer.score)
        val playerDiff = abs(BLACKJACK_SCORE - player.score)

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
