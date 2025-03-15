package blackjack.domain.state

import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Player

enum class ResultState {
    WIN,
    BLACKJACK_WIN,
    LOSE,
    DRAW,
    ;

    companion object {
        fun from(
            player: Player,
            dealer: Dealer,
        ): ResultState {
            val playerScore = player.score()
            val dealerScore = dealer.score()

            return when {
                player.isBlackjack() -> BLACKJACK_WIN
                player.isBust() -> LOSE
                dealer.isBust() -> WIN
                playerScore > dealerScore -> WIN
                playerScore < dealerScore -> LOSE
                else -> DRAW
            }
        }
    }
}
