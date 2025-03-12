package blackjack.domain.state

import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Player

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
            if (player.isBust()) return LOSE
            if (dealer.isBust()) return WIN

            val playerScore = player.score()
            val dealerScore = dealer.score()

            return when {
                playerScore > dealerScore -> WIN
                playerScore < dealerScore -> LOSE
                else -> DRAW
            }
        }
    }
}
