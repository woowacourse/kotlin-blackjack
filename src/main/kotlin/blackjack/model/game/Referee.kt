package blackjack.model.game

import blackjack.model.player.Dealer
import blackjack.model.player.PlayerEntry

class Referee(val dealer: Dealer, val playerEntry: PlayerEntry) {
    fun makeResults(): List<Result> {
        return playerEntry.players.map { player -> determineWinner(dealer.hand.totalScore, player.hand.totalScore) }
    }

    private fun determineWinner(
        dealerScore: Int,
        playerScore: Int,
    ): Result {
        if (playerScore > 21) return Result.DEALER_WIN
        if (dealerScore > 21) return Result.PLAYER_WIN
        return when {
            playerScore > dealerScore -> Result.PLAYER_WIN
            playerScore < dealerScore -> Result.DEALER_WIN
            else -> Result.DRAW
        }
    }
}
