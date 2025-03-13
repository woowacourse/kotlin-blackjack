package blackjack.domain

import blackjack.domain.BlackJackGame.Companion.BUST_STANDARD

enum class GameResult {
    WIN,
    LOSE,
    PUSH,
    ;

    companion object {
        fun from(
            dealerSum: Int,
            playerSum: Int,
            isPlayer: Boolean,
        ): GameResult {
            return when {
                (dealerSum > BUST_STANDARD) && (playerSum > BUST_STANDARD) -> if (isPlayer) LOSE else WIN
                dealerSum > BUST_STANDARD -> if (isPlayer) WIN else LOSE
                playerSum > BUST_STANDARD || playerSum < dealerSum -> if (isPlayer) LOSE else WIN
                playerSum > dealerSum -> if (isPlayer) WIN else LOSE
                else -> PUSH
            }
        }
    }
}
