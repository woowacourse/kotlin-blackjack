enum class GameResult(val description: String) {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    operator fun not(): GameResult = if (this == WIN) {
        LOSE
    } else if (this == LOSE) {
        WIN
    } else {
        DRAW
    }

    companion object {
        private const val EXCEPTION_CASE = "처리하지 못한 케이스입니다"

        fun valueOf(playerScore: Int, dealerScore: Int): GameResult =
            GameResultCondition.values().find { gameResultCondition ->
                gameResultCondition.condition(playerScore, dealerScore)
            }?.gameResult ?: throw IllegalStateException(EXCEPTION_CASE)
    }
}
