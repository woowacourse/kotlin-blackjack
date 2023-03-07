package domain.judge

import domain.gamer.cards.DealerCards
import domain.gamer.cards.PlayerCards
import domain.player.Player

class Referee(private val dealerState: DealerCards, private val players: List<Player>) {

    fun judgePlayersResult(): List<ParticipantResult> =
        players.map {
            ParticipantResult(it.name, judgePlayerResult(it.ownCards as PlayerCards))
        }

    private fun judgePlayerResult(player: PlayerCards): Result {
        val playerSum = player.calculateCardSum()
        val dealerSum = dealerState.calculateCardSum()

        return judgeWithConditions(playerSum, dealerSum)
    }

    private fun judgeWithConditions(playerSum: Int, dealerSum: Int): Result {
        return when {
            playerSum > CARD_SUM_MAX_VALUE -> Result.LOSS
            dealerSum > CARD_SUM_MAX_VALUE -> Result.WIN
            dealerSum > playerSum -> Result.LOSS
            playerSum == CARD_SUM_MAX_VALUE && dealerSum == CARD_SUM_MAX_VALUE -> Result.LOSS
            playerSum > dealerSum -> Result.WIN
            else -> Result.DRAW
        }
    }

    companion object {
        const val CARD_SUM_MAX_VALUE = 21
    }
}
