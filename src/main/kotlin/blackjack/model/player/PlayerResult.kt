package blackjack.model.player

import Player
import blackjack.model.game.Result

class PlayerResult(val player: Player, val result: Result) {
    val profit: Int by lazy { calculateProfits() }

    private fun calculateProfits(): Int {
        val playerBlackjack = player.hand.isBlackjack()
        val bettingAmount = player.bettingMoney.bettingMoney

        return when {
            result == Result.PLAYER_WIN && playerBlackjack -> (bettingAmount * 1.5).toInt()
            result == Result.PLAYER_WIN -> bettingAmount
            result == Result.DEALER_WIN -> -bettingAmount
            result == Result.DRAW -> 0
            else -> 0
        }
    }
}
