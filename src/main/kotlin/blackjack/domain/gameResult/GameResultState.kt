package blackjack.domain.gameResult

import blackjack.domain.gameResult.state.State

data class GameResultState(val state: State, val gameResult: GameResult) {
    fun getEarnRate(): Double = state.earnRate * gameResult.sign
}
