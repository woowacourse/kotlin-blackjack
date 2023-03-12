package blackjack.domain.gameResult.playerCase

import blackjack.domain.card.CardsState
import blackjack.domain.gameResult.GameResult

enum class RunningCase(
    val gameResult: GameResult,
) {

    WIN(GameResult.WIN),
    LOSE(GameResult.LOSE),
    DRAW(GameResult.DRAW);

    companion object {

        fun valueOf(playerScore: Int, dealerCardsState: CardsState): RunningCase {
            return when (dealerCardsState) {
                is CardsState.Bust -> WIN
                is CardsState.BlackJack -> LOSE
                is CardsState.Running -> decideByScore(playerScore, dealerCardsState.score)
            }
        }

        private fun decideByScore(playerScore: Int, dealerCardsScore: Int): RunningCase {
            return when {
                playerScore > dealerCardsScore -> WIN
                playerScore < dealerCardsScore -> LOSE
                else -> DRAW
            }
        }
    }
}
