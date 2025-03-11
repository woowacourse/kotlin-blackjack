package blackjack.domain.state

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
            if (player.gameState == PersonState.BUST) return LOSE
            if (dealer.gameState == PersonState.BUST) return WIN

            val playerScore = player.score
            val dealerScore = dealer.score

            return when {
                playerScore > dealerScore -> WIN
                playerScore < dealerScore -> LOSE
                else -> DRAW
            }
        }
    }
}
