package blackjack.model.game

import blackjack.model.user.Participant.Dealer
import blackjack.model.user.Participant.Player

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
            val playerScore = Score(player.gameInformation.hand.cards).point
            val dealerScore = Score(dealer.gameInformation.hand.cards).point
            return when {
                playerScore > dealerScore -> WIN
                playerScore < dealerScore -> DEFEAT
                else -> TIE
            }
        }
    }
}
