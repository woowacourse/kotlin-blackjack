package blackjack.domain.state

import blackjack.const.GameRule
import blackjack.domain.person.Dealer
import blackjack.domain.person.Player

enum class ResultState {
    WIN,
    LOSE,
    DRAW,
    ;

    companion object {
        fun calculateWin(
            player: Player,
            dealer: Dealer,
        ): ResultState {
            val playerScore = player.score()
            val dealerScore = dealer.score()

            if (playerScore > GameRule.BLACKJACK_SCORE) return LOSE
            if (dealerScore > GameRule.BLACKJACK_SCORE) return WIN

            return when {
                playerScore > dealerScore -> WIN
                playerScore < dealerScore -> LOSE
                else -> DRAW
            }
        }
    }
}
