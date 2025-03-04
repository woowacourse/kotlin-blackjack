package blackjack.domain

enum class ResultState {
    WIN,
    LOSE,
    DRAW,
    ;

    companion object {
        fun calculateWin(
            score: Int,
            dealerScore: Int,
        ): ResultState {
            if (score > 21) return LOSE

            return when {
                score > dealerScore -> WIN
                score < dealerScore -> LOSE
                else -> DRAW
            }
        }
    }
}
