package blackjack.domain.gameResult

import blackjack.domain.participant.Player

data class PlayerResult(val player: Player, val resultState: ResultState) {
    fun getEarn(): Int = (resultState.getEarnRate() * player.bettingAmount).toInt()
}
