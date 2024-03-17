package blackjack.model.role

import blackjack.model.config.GameRule.BLACK_JACK_SCORE
import blackjack.model.result.WinningResultStatus

class Player(override val name: PlayerName, private val money: Int = 0) : Role(name) {
    fun judgeWinning(dealer: Dealer): WinningResultStatus {
        val score = state.getCardHandScore()
        val dealerScore = dealer.state.getCardHandScore()
        return when {
            score > BLACK_JACK_SCORE -> WinningResultStatus.DEFEAT
            dealerScore > BLACK_JACK_SCORE -> WinningResultStatus.VICTORY
            dealerScore > score -> WinningResultStatus.DEFEAT
            score > dealerScore -> WinningResultStatus.VICTORY
            dealerScore == score -> WinningResultStatus.DRAW
            else -> WinningResultStatus.VICTORY
        }
    }

    fun calculateProfit(dealer: Dealer): Double = state.calculateProfit(money, dealer.state)
}
