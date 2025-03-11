package blackjack.domain

import blackjack.domain.BlackJackGame.Companion.BUST_STANDARD

enum class GameResult {
    WIN,
    LOSE,
    PUSH,
    ;

    companion object {
        fun resultOfPlayer(
            dealerSum: Int,
            playerSum: Int,
        ): GameResult {
            return when {
                (dealerSum > BUST_STANDARD) && (playerSum <= BUST_STANDARD) -> WIN
                playerSum > BUST_STANDARD -> LOSE
                playerSum > dealerSum -> WIN
                playerSum < dealerSum -> LOSE
                else -> PUSH
            }
        }

        fun resultOfDealer(
            dealerSum: Int,
            playerSum: Int,
        ): GameResult {
            return when {
                (dealerSum > BUST_STANDARD) && (playerSum > BUST_STANDARD) -> WIN
                dealerSum > BUST_STANDARD -> LOSE
                playerSum > BUST_STANDARD -> WIN
                playerSum > dealerSum -> LOSE
                playerSum < dealerSum -> WIN
                else -> PUSH
            }
        }
    }
}
