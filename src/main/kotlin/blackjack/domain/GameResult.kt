package blackjack.domain

enum class GameResult {
    승, 무, 패;

    companion object {
        private const val BLACKJACK_SCORE = 21

        fun blackjackScore(): Int = BLACKJACK_SCORE

        fun judgePlayer(dealerScore: Int, playerScore: Int): GameResult = when {
            playerScore > BLACKJACK_SCORE -> 패
            dealerScore > BLACKJACK_SCORE || playerScore > dealerScore -> 승
            dealerScore == playerScore -> 무
            else -> 패
        }
    }
}
