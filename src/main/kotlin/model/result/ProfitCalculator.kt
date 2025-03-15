package model.result

import model.participant.Dealer
import model.participant.Player
import model.participant.Players

class ProfitCalculator(private val dealer: Dealer, players: Players) {
    val playerProfits: List<PlayerProfit> =
        players.map { player -> PlayerProfit(player.name, calculatePlayerProfit(player)) }

    val dealerProfit: DealerProfit = DealerProfit(playerProfits.map { -it.money }.sum())

    private fun calculatePlayerProfit(player: Player): Float =
        when {
            player.isBlackJack && !dealer.isBlackJack -> player.betAmount * ONE_AND_HALF
            player.isBlackJack && dealer.isBlackJack -> ZERO
            dealer.isBust -> player.betAmount
            player.isBust -> -player.betAmount
            else -> compareScores(player)
        }

    private fun compareScores(player: Player): Float {
        val dealerScore = dealer.score
        val playerScore = player.score
        return when {
            playerScore < dealerScore -> -player.betAmount
            playerScore > dealerScore -> player.betAmount
            else -> ZERO
        }
    }

    companion object {
        const val BLACKJACK_SCORE = 21
        private const val ZERO = 0f
        private const val ONE_AND_HALF = 1.5f
    }
}
