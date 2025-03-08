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
        ): GameResult {
            if (isPlayerSumBust(playerSum)) return LOSE
            if (isDealerSumBust(dealerSum)) return WIN
            return compareScores(dealerSum, playerSum)
        }

        private fun isPlayerSumBust(playerSum: Int): Boolean = playerSum > BUST_STANDARD

        private fun isDealerSumBust(dealerSum: Int): Boolean = dealerSum > BUST_STANDARD

        private fun compareScores(
            dealerSum: Int,
            playerSum: Int,
        ): GameResult = if (dealerSum > playerSum) LOSE else WIN
    }
}
