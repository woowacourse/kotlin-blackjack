package blackjack.domain.gameResult

import blackjack.domain.gameResult.state.BlackJackRule
import blackjack.domain.participant.Player

data class PlayerResult(val blackJackRule: BlackJackRule<Player>, val gameResult: GameResult) {
    fun getProfit(): Int = (blackJackRule.getEarnRate(gameResult) * blackJackRule.participant.bettingAmount).toInt()
}
