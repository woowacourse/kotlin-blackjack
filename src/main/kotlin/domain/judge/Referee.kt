package domain.judge

import domain.Player
import domain.gamer.cards.DealerCards
import domain.gamer.cards.PlayerCards

class Referee(private val dealerState: DealerCards, private val players: List<Player>) {

    fun judgePlayersResult(): Map<String, Result> = mutableMapOf<String, Result>().apply {
        players.forEach {
            this[it.name] = judgePlayerResult(it.state)
        }
    }

    private fun judgePlayerResult(player: PlayerCards): Result {
        val playerSum = player.calculateCardSum()
        val dealerSum = dealerState.calculateCardSum()

        return judgeWithConditions(playerSum, dealerSum)
    }

    private fun judgeWithConditions(playerSum: Int, dealerSum: Int): Result {
        return when {
            playerSum > CARD_SUM_MAX_VALUE -> Result.LOSS
            dealerSum.checkPlayerLossCondition(playerSum) -> Result.LOSS
            playerSum > dealerSum -> Result.WIN
            dealerSum > CARD_SUM_MAX_VALUE -> Result.WIN
            else -> Result.DRAW
        }
    }

    private fun Int.checkPlayerLossCondition(playerSum: Int) = this >= playerSum && this <= CARD_SUM_MAX_VALUE

    companion object {
        const val CARD_SUM_MAX_VALUE = 21
    }
}
