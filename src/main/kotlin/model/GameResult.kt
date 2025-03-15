package model

import kotlin.math.abs

data class PlayerResult(val player: Player, val result: GameResult)

enum class GameResult(val profitRate: Float) {
    WIN(1f),
    LOSE(-1f),
    PUSH(0f),
    BLACKJACK(1.5f),
    ;

    companion object {
        const val BLACKJACK_SCORE = 21

        fun compareWinOrLose(
            dealer: Dealer,
            players: Players,
        ): List<PlayerResult> {
            val playerResults: List<PlayerResult> =
                players.map { player ->
                    PlayerResult(player, decideResult(dealer, player))
                }
            return playerResults
        }

        private fun decideResult(
            dealer: Dealer,
            player: Player,
        ): GameResult {
            return when {
                dealer.isBust() -> WIN
                player.isBust() -> LOSE
                dealer.isBlackJack() && player.isBlackJack() -> PUSH
                player.isBlackJack() -> BLACKJACK
                dealer.getTotalScore() == player.getTotalScore() -> PUSH
                else -> compareScore(dealer, player)
            }
        }

        private fun compareScore(
            dealer: Dealer,
            player: Player,
        ): GameResult {
            val dealerDiff = abs(BLACKJACK_SCORE - dealer.getTotalScore())
            val playerDiff = abs(BLACKJACK_SCORE - player.getTotalScore())
            return when {
                playerDiff < dealerDiff -> WIN
                playerDiff > dealerDiff -> LOSE
                else -> PUSH
            }
        }
    }
}
