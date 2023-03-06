package blackjack.domain

import blackjack.domain.BlackJack.Companion.blackjackScore

enum class GameResult {
    승, 무, 패;

    companion object {
        fun judgePlayer(dealerScore: Int, playerScore: Int): GameResult = when {
            playerScore > blackjackScore() -> 패
            dealerScore > blackjackScore() || playerScore > dealerScore -> 승
            dealerScore == playerScore -> 무
            else -> 패
        }
    }
}
