package blackjack.domain.gameResult

import blackjack.domain.gameResult.state.State

data class ResultState(val state: State, val result: Result) {
    fun getEarnRate(): Double = state.earnRate * result.sign
}
