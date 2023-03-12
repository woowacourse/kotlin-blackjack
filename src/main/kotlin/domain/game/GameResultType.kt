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
                is ScoreState.Normal -> getReusltWithPlayerNormal(dealerScoreState, playerScoreState.value)
            }
        }

        fun getResultWithPlayerBlackJack(dealerScoreState: ScoreState): GameResultType {
            return when (dealerScoreState) {
                is ScoreState.Burst -> BLACKJACK
                is ScoreState.BlackJack -> DRAW
                is ScoreState.Normal -> BLACKJACK
            }
        }

        fun getReusltWithPlayerNormal(dealerScoreState: ScoreState, playerScoreValue: Int): GameResultType {
            return when (dealerScoreState) {
                is ScoreState.Burst -> WIN
                is ScoreState.BlackJack -> LOSE
                is ScoreState.Normal -> compareScoreValue(dealerScoreState.value, playerScoreValue)
            }
        }

        fun compareScoreValue(dealerScoreValue: Int, playerScoreValue: Int): GameResultType {
            return when (playerScoreValue - dealerScoreValue) {
                DRAW_NUMBER -> DRAW
                in POSITIVE_RANGE -> WIN
                in NEGATIVE_RANGE -> LOSE
                else -> throw IllegalStateException(GAME_RESULT_ERROR)
            }
        }

        private const val GAME_RESULT_ERROR = "[ERROR] 승패 반환 오류가 발생하였습니다!!"
        private const val DRAW_NUMBER = 0
        private val POSITIVE_RANGE = 1..Int.MAX_VALUE
        private val NEGATIVE_RANGE = Int.MIN_VALUE..-1
    }
}
