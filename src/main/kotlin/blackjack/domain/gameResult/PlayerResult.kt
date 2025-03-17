package blackjack.domain.gameResult

import blackjack.domain.gameResult.state.BlackJackRole
import blackjack.domain.participant.Player

data class PlayerResult(val blackJackRole: BlackJackRole<Player>, val gameResult: GameResult) {
    fun getProfit(): Int = (blackJackRole.getEarnRate(gameResult) * blackJackRole.participant.bettingAmount).toInt()
}
