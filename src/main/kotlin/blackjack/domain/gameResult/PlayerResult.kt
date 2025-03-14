package blackjack.domain.gameResult

import blackjack.domain.gameResult.state.PlayerState

data class PlayerResult(val state: PlayerState, val gameResult: GameResult) {
    fun getProfit(): Int = (state.earnRate * gameResult.sign * state.player.bettingAmount).toInt()
}
