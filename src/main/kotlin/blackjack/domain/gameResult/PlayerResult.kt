package blackjack.domain.gameResult

import blackjack.domain.participant.Player

data class PlayerResult(val player: Player, val gameResultState: GameResultState) {
    fun getEarn(): Int = (gameResultState.getEarnRate() * player.bettingAmount).toInt()
}
