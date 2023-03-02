package blackjack.domain

enum class GameResult {
    WIN, DRAW, LOSE;

    companion object {
        private const val BLACKJACK_SCORE = 21

        fun blackjackScore(): Int = BLACKJACK_SCORE

        fun judgePlayer(dealerScore: Int, playerScore: Int): GameResult = when {
            playerScore > BLACKJACK_SCORE -> LOSE
            dealerScore > BLACKJACK_SCORE || playerScore > dealerScore -> WIN
            dealerScore == playerScore -> DRAW
            else -> LOSE
        }
    }
}
