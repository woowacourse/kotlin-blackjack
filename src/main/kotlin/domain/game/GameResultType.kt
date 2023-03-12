package domain.game

import domain.card.ScoreState

enum class GameResultType(val value: String, val profitRate: Double) {
    WIN("승", 1.0),
    DRAW("무", 0.0),
    LOSE("패", -1.0),
    BLACKJACK("블랙잭", 1.5),
    ;

    companion object {
        fun valueOf(dealerScoreState: ScoreState, playerScoreState: ScoreState): GameResultType {
            return when (playerScoreState) {
                is ScoreState.Burst -> LOSE
                is ScoreState.BlackJack -> getResultWithPlayerBlackJack(dealerScoreState)
                is ScoreState.Normal -> getResultWithPlayerNormal(dealerScoreState, playerScoreState.value)
            }
        }

        private fun getResultWithPlayerBlackJack(dealerScoreState: ScoreState): GameResultType {
            return when (dealerScoreState) {
                is ScoreState.Burst -> BLACKJACK
                is ScoreState.BlackJack -> DRAW
                is ScoreState.Normal -> BLACKJACK
            }
        }

        private fun getResultWithPlayerNormal(dealerScoreState: ScoreState, playerScoreValue: Int): GameResultType {
            return when (dealerScoreState) {
                is ScoreState.Burst -> WIN
                is ScoreState.BlackJack -> LOSE
                is ScoreState.Normal -> compareScoreValue(dealerScoreState.value, playerScoreValue)
            }
        }

        private fun compareScoreValue(dealerScoreValue: Int, playerScoreValue: Int): GameResultType {
            return when {
                playerScoreValue > dealerScoreValue -> WIN
                dealerScoreValue > playerScoreValue -> LOSE
                else -> DRAW
            }
        }
    }
}
