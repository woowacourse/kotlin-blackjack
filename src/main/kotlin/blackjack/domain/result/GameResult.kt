package blackjack.domain.result

enum class GameResult {
    WIN, DRAW, LOSE;

    companion object {
        fun getWinLoseDraw(scoreGap: Int): GameResult = when {
            scoreGap > 0 -> WIN
            scoreGap == 0 -> DRAW
            else -> LOSE
        }
    }
}
