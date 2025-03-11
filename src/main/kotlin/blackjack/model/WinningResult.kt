package blackjack.model

enum class WinningResult {
    WIN,
    PUSH,
    LOSE,
    ;

    companion object {
        fun from(
            playerScore: Int,
            dealerScore: Int,
            playerBust: Boolean,
            dealerBust: Boolean,
        ): WinningResult =
            when {
                playerBust -> LOSE
                dealerBust -> WIN
                playerScore > dealerScore -> WIN
                playerScore < dealerScore -> LOSE
                else -> PUSH
            }
    }
}
