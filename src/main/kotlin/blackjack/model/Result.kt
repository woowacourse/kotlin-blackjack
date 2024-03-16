package blackjack.model

import blackjack.model.Participant.Dealer
import blackjack.model.Participant.Player

enum class Result {
    WIN,
    DEFEAT,
    TIE, ;

    companion object {
        fun judgeResult(
            dealer: Dealer,
            player: Player,
        ): Result {
            if (player.gameInformation.state == GameState.Finished.BUST) return DEFEAT
            if (dealer.gameInformation.state == GameState.Finished.BUST) return WIN
            val playerScore = Calculator.calculateScore(player.gameInformation.cards)
            val dealerScore = Calculator.calculateScore(dealer.gameInformation.cards)
            return when {
                playerScore > dealerScore -> WIN
                playerScore < dealerScore -> DEFEAT
                else -> TIE
            }
        }
    }
}
