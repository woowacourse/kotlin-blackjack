package blackjack.model.game

import Player
import blackjack.model.player.Dealer
import blackjack.model.player.PlayerEntry
import blackjack.model.player.PlayerResult

class Referee(val dealer: Dealer, val playerEntry: PlayerEntry) {
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
        val playerBlackjack = player.hand.isBlackjack()
        val dealerBlackjack = dealer.hand.isBlackjack()
        if (playerBlackjack && dealerBlackjack) return Result.DRAW
        if (playerBlackjack) return Result.PLAYER_WIN
        if (dealerBlackjack) return Result.DEALER_WIN
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
