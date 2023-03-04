package blackjack.domain.gameResult

enum class GameResult {
    WIN,
    LOSE,
    DRAW;

    operator fun not(): GameResult = if (this == WIN) {
        LOSE
    } else if (this == LOSE) {
        WIN
    } else {
        DRAW
    }

    companion object {
        private const val EXCEPTION_CASE = "[ERROR] 처리하지 못한 케이스입니다"

        fun valueOf(playerScore: Int, dealerScore: Int): GameResult = GameResultCondition
            .values()
            .find { gameResultCondition ->
                gameResultCondition.condition(playerScore, dealerScore)
            }?.gameResult ?: throw IllegalStateException(EXCEPTION_CASE)
    }
}
