package blackjack.domain.state

import blackjack.domain.Score
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
            return calculateWinFromPersonState(dealer, player) ?: compareScores(player.score(), dealer.score())
        }

        private fun calculateWinFromPersonState(
            dealer: Dealer,
            player: Player,
        ): ResultState? =
            when {
                player.gameState == PersonState.BUST -> LOSE
                dealer.gameState == PersonState.BUST -> WIN
                player.gameState == PersonState.BLACKJACK && dealer.gameState != PersonState.BLACKJACK -> WIN
                else -> null
            }

        private fun compareScores(
            playerScore: Score,
            dealerScore: Score,
        ): ResultState =
            when {
                playerScore.value > dealerScore.value -> WIN
                playerScore.value < dealerScore.value -> LOSE
                else -> DRAW
            }
    }
}
