package blackjack.domain

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player

class GameResult(private val dealer: Dealer, private val players: List<Player>) {
    fun getGameResult(): GameResult {
        players.forEach { player ->
            updateResults(player, getPlayerResultStatus(player))
        }
        return this
    }

    fun getPlayerResultStatus(player: Player): GameResultStatus {
        return when {
            player.hand.isBlackJack() && !dealer.hand.isBlackJack() -> GameResultStatus.PLAYER_WIN
            player.hand.isBlackJack() && dealer.hand.isBlackJack() -> GameResultStatus.DRAW
            dealer.hand.isBlackJack() && !player.hand.isBlackJack() -> GameResultStatus.PLAYER_LOSE

            player.hand.isBust() -> GameResultStatus.PLAYER_LOSE
            dealer.hand.isBust() -> GameResultStatus.PLAYER_WIN

            dealer.totalSum > player.totalSum -> GameResultStatus.PLAYER_LOSE
            player.totalSum > dealer.totalSum -> GameResultStatus.PLAYER_WIN
            else -> GameResultStatus.DRAW
        }
    }

    private fun updateResults(
        player: Player,
        status: GameResultStatus,
    ) {
        when (status) {
            GameResultStatus.PLAYER_WIN -> {
                player.result.addWin()
                dealer.result.addLose()
            }

            GameResultStatus.PLAYER_LOSE -> {
                player.result.addLose()
                dealer.result.addWin()
            }

            GameResultStatus.DRAW -> {
                player.result.addDraw()
                dealer.result.addDraw()
            }
        }
    }
}
