package blackjack.domain.state

import blackjack.domain.calculateScore
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
            if (player.gameState == PlayerState.BUST) return LOSE
            if (dealer.gameState == DealerState.BUST) return WIN

            val playerScore = player.calculateScore()
            val dealerScore = dealer.calculateScore()

            return when {
                playerScore > dealerScore -> WIN
                playerScore < dealerScore -> LOSE
                else -> DRAW
            }
        }
    }
}
