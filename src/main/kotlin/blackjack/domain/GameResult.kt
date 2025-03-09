package blackjack.domain

import blackjack.domain.BlackJackGame.Companion.BUST_STANDARD

enum class GameResult {
    WIN,
    LOSE,
    ;

    companion object {
        fun from(
            dealerSum: Int,
            playerSum: Int,
        ): GameResult =
            if ((dealerSum > BUST_STANDARD) && (playerSum < BUST_STANDARD)) {
                WIN
            } else if (playerSum > BUST_STANDARD) {
                LOSE
            } else {
                if (dealerSum > playerSum) {
                    LOSE
                } else {
                    WIN
                }
            }
    }
}
