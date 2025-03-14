package blackjack.domain.gameResult

import blackjack.domain.gameResult.state.State
import blackjack.domain.participant.Player

data class PlayerResult(val state: State<Player>, val gameResult: GameResult) {
    fun getProfit(): Int = (state.earnRate * gameResult.sign * state.participant.bettingAmount).toInt()
}
