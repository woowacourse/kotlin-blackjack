package blackjack.domain

enum class GameResult(val description: String) {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    operator fun not(): GameResult {
        return when (this) {
            WIN -> LOSE
            LOSE -> WIN
            DRAW -> DRAW
        }
    }

    companion object {
        private const val EXCEPTION_CASE = "[ERROR] 처리하지 못한 케이스입니다"

        fun valueOf(playerScore: Int, dealerScore: Int): GameResult =
            GameResultCondition.values().find { gameResultCondition ->
                gameResultCondition.condition(playerScore, dealerScore)
            }?.gameResult ?: throw IllegalStateException(EXCEPTION_CASE)
    }
}
