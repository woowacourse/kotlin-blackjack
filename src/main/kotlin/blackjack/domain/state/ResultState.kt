package blackjack.domain.state

import blackjack.const.GameRule

enum class ResultState {
    WIN,
    LOSE,
    DRAW,
    ;

    companion object {
        fun calculateWin(
            playerScore: Int,
            dealerScore: Int,
        ): ResultState {
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
