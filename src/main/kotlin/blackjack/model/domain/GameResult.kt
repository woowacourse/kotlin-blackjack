package blackjack.model.domain

enum class GameResult {
    Win,
    Lose,
    Draw,
    ;

    companion object {
        fun compare(
            target: Int,
            other: Int,
        ): GameResult {
            if (target < other) {
                return Lose
            } else if (target > other) {
                return Win
            }
            return Draw
        }
    }
}
