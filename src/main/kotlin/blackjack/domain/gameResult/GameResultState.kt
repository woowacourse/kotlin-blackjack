package blackjack.domain.gameResult

import blackjack.domain.gameResult.state.PlayerState

data class GameResultState(val state: PlayerState, val gameResult: GameResult) {
    fun getEarn(): Int = (state.earnRate * gameResult.sign * state.player.bettingAmount).toInt()
}
