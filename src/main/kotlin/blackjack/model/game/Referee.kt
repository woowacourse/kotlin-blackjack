package blackjack.model.game

import Player
import blackjack.model.player.Dealer
import blackjack.model.player.PlayerEntry

class Referee(val dealer: Dealer, val playerEntry: PlayerEntry) {
    fun makeResults(): List<Result> {
        return playerEntry.players.map { player -> determineWinner(dealer, player) }
    }

    private fun determineWinner(
        dealer: Dealer,
        player: Player,
    ): Result {
        if (player.state == State.Finished.Bust) return Result.DEALER_WIN
        if (dealer.state == State.Finished.Bust) return Result.PLAYER_WIN
        val playerScore = player.hand.totalScore
        val dealerScore = dealer.hand.totalScore
        return when {
            playerScore > dealerScore -> Result.PLAYER_WIN
            playerScore < dealerScore -> Result.DEALER_WIN
            else -> Result.DRAW
        }
    }
}
