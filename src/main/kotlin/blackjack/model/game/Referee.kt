package blackjack.model.game

import Player
import blackjack.model.player.Dealer
import blackjack.model.player.PlayerEntry
import blackjack.model.player.PlayerResult

class Referee(val dealer: Dealer, val playerEntry: PlayerEntry) {
    fun makeResults(): List<Result> {
        return playerEntry.players.map { player -> determineWinner(dealer, player) }
    }

    fun judgeGame(): GameResult {
        val playerResults =
            playerEntry.players.map { player ->
                val result = determineWinner(dealer, player)
                PlayerResult(player, result)
            }
        return GameResult(dealer, playerResults)
    }

    private fun determineWinner(
        dealer: Dealer,
        player: Player,
    ): Result {
        if (player.hand.isBlackjack() && dealer.hand.isBlackjack()) return Result.DRAW
        if (player.hand.isBlackjack()) return Result.PLAYER_WIN
        if (dealer.hand.isBlackjack()) return Result.DEALER_WIN
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
