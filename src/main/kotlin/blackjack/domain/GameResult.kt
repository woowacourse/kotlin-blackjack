package blackjack.domain

enum class GameResult(val dividendRate: Double) {
    LOSE(-1.0), // 패
    DRAW(0.0), // 무
    BLACKJACK(1.5), // 블랙잭으로 승
    WIN(1.0); // 그냥 승

    operator fun not(): GameResult {
        return when (this) {
            BLACKJACK, WIN -> LOSE
            LOSE -> WIN
            DRAW -> DRAW
        }
    }

    companion object {
        private const val EXCEPTION_CASE = "[ERROR] 처리하지 못한 케이스입니다"

        fun valueOf(playerScore: Int, dealerScore: Int, isPlayerBlackJack: Boolean): GameResult =
            GameResultCondition.values().find { gameResultCondition ->
                gameResultCondition.scoreCondition(playerScore, dealerScore) && gameResultCondition.blackJackCondition(isPlayerBlackJack)
            }?.gameResult ?: throw IllegalStateException(EXCEPTION_CASE)
    }
}
