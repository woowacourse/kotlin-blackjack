package domain.judge

import domain.gamer.state.DealerState
import domain.gamer.state.PlayerState

class Referee(private val dealerState: DealerState, private val players: Map<String, PlayerState>) {

    fun judgePlayersResult(): Map<String, Result> = mutableMapOf<String, Result>().apply {
        players.forEach {
            this[it.key] = judgePlayerResult(it.value)
        }
    }

    private fun judgePlayerResult(player: PlayerState): Result {
        val playerSum = player.calculateCardSum()
        val dealerSum = dealerState.calculateCardSum()

        return when {
            playerSum > CARD_SUM_MAX_VALUE || dealerSum.checkPlayerLossCondition(playerSum) -> Result.LOSS
            playerSum > dealerSum || dealerSum > CARD_SUM_MAX_VALUE -> Result.WIN
            else -> Result.DRAW
        }
    }

    private fun Int.checkPlayerLossCondition(playerSum: Int) = this > playerSum && this <= CARD_SUM_MAX_VALUE

    companion object {
        const val CARD_SUM_MAX_VALUE = 21
    }
}
