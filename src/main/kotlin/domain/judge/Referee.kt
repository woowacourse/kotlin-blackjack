package domain.judge

import domain.gamer.cards.DealerCards
import domain.gamer.cards.ParticipantCards
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

        return when {
            checkBlackJack(player) && checkBlackJack(dealerState) -> Result.DRAW
            checkBlackJack(player) -> Result.WIN
            else -> judgeWithConditions(playerSum, dealerSum)
        }
    }

    private fun checkBlackJack(participant: ParticipantCards): Boolean =
        participant.cards.size == BLACKJACK_SIZE && participant.calculateCardSum() == CARD_SUM_MAX_VALUE

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
        private const val BLACKJACK_SIZE = 2
        const val CARD_SUM_MAX_VALUE = 21
    }
}
